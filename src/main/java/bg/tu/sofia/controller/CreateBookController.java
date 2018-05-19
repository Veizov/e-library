package bg.tu.sofia.controller;

import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.BookCategory;
import bg.tu.sofia.service.BookCategoryService;
import bg.tu.sofia.service.BookService;
import bg.tu.sofia.utils.FileUtils;
import bg.tu.sofia.utils.Utils;
import bg.tu.sofia.validator.BookValidator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
@RequestMapping("/admin")
public class CreateBookController {
    private static Logger LOG = LoggerFactory.getLogger(CreateBookController.class);
    private static final Integer MAX_BOOK_COVER_FILE_SIZE = 1_048_576; //1 MB
    private static final Integer MAX_BOOKS_FOLDER_SIZE = 524_288_000; //500 MB
    private static final Integer MIN_BYTE_ARRAY_LENGTH = 1;
    private static final Integer PDF_FILES_PER_ITERATION = 5;
    private static final String DEFAULT_CATEGORY = "Other";

    private Boolean inProgress = false;
    private Integer progress = 0;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private BookCategoryService categoryService;

    @RequestMapping(value = "/create-book", method = RequestMethod.GET)
    public String createBook(HttpServletRequest request, Model model, @RequestParam(value = "id", required = false) Integer bookID) {
        Book book = bookService.findById(bookID);

        if (!model.containsAttribute("book") && book == null) {
            request.getSession().setAttribute("coverImage", new Blobs());
            request.getSession().setAttribute("bookFile", new Blobs());
            model.addAttribute("book", new Book());
        }

        if (!model.containsAttribute("book") && book != null) {
            request.getSession().setAttribute("coverImage", book.getCover() != null ? book.getCover() : new Blobs());
            request.getSession().setAttribute("bookFile", book.getFile() != null ? book.getFile() : new Blobs());
            model.addAttribute("book", book);
        }

        List<BookCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/create-book";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(RedirectAttributes redirectAttributes, HttpServletRequest request, @ModelAttribute Book book, BindingResult bindingResult,
                             @RequestParam("imageFile") MultipartFile imageFileRequest,
                             @RequestParam("bookFile") MultipartFile bookFileRequest) {

        try {
            Blobs coverImage = (Blobs) request.getSession().getAttribute("coverImage");
            fillBlob(imageFileRequest, coverImage);
            book.setCover(coverImage);

            Blobs bookFile = (Blobs) request.getSession().getAttribute("bookFile");
            fillBlob(bookFileRequest, bookFile);
            book.setFile(bookFile);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book", bindingResult);
            redirectAttributes.addFlashAttribute("book", book);

            if (book.getId() != null)
                redirectAttributes.addAttribute("id", book.getId());

            return "redirect:/admin/create-book";
        } else {
            byte[] bookCoverContent = book.getCover().getContent();
            if (null == bookCoverContent || MIN_BYTE_ARRAY_LENGTH > bookCoverContent.length)
                book.setCover(null);

            book.setNumberOfPages(Utils.getNumberOfPages(book.getFile().getContent()));
            book.setCreatedDate(new Date());
            bookService.save(book);

            request.getSession().removeAttribute("coverImage");
            request.getSession().removeAttribute("bookFile");
            return "redirect:/books";
        }
    }

    @RequestMapping(value = "/edit-book", method = RequestMethod.GET)
    public String editBook(Model model,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request,
                           @RequestParam("bookID") Integer bookID) {
        Book book = bookService.findById(bookID);
        if (Objects.nonNull(book))
            redirectAttributes.addFlashAttribute("book", book);

        return "redirect:/admin/create-book";
    }

    @RequestMapping("/validate-uploaded-file")
    @ResponseBody
    public List<String> validateUploadedBookCover(@RequestParam("fileName") String fileName, @RequestParam("size") Integer size) {
        List<String> errors = new ArrayList<String>();
        String format = fileName.substring(fileName.lastIndexOf(".", fileName.length()));
        if (!format.equalsIgnoreCase(".jpg") && !format.equalsIgnoreCase(".png")) {
            String message = messageSource.getMessage("cover.upload.valid", null, LocaleContextHolder.getLocale());
            errors.add(message);
        }
        if (MAX_BOOK_COVER_FILE_SIZE < size) {
            String message = messageSource.getMessage("cover.upload.size.valid", null, LocaleContextHolder.getLocale());
            errors.add(message);
        }
        return errors;
    }

    @RequestMapping("/validate-uploaded-book-file")
    @ResponseBody
    public List<String> validateUploadedBookFile(@RequestParam("fileName") String fileName) {
        List<String> errors = new ArrayList<String>();
        String format = fileName.substring(fileName.lastIndexOf(".", fileName.length()));
        if (!format.equalsIgnoreCase(".pdf")) {
            String message = messageSource.getMessage("pdf.upload.valid", null, LocaleContextHolder.getLocale());
            errors.add(message);
        }
        return errors;
    }

    private void fillBlob(@RequestParam("imageFile") MultipartFile imageFile, Blobs blobs) throws IOException {
        if (null != imageFile && !StringUtils.isEmpty(imageFile.getOriginalFilename())) {
            blobs.setFilename(imageFile.getOriginalFilename());
            blobs.setContentType(imageFile.getContentType());
            blobs.setContent(imageFile.getBytes());
            blobs.setCreatedDate(new Date());
            blobs.setFilesize((int) imageFile.getSize());
        }
    }

    @RequestMapping(value = "/create-books-folder", method = RequestMethod.GET)
    public String createBooksFromFolder(HttpServletRequest request, Model model) {
        model.addAttribute("inProgress", inProgress);
        return "admin/create-books-folder";
    }

    @RequestMapping(value = "/create-books-folder", method = RequestMethod.POST)
    public String createBooksFromFolder(Model model, @RequestParam("folder") String folderPath) {
        if (!inProgress) {

            inProgress = true;
            progress = 0;

            try {
                Path path = Paths.get(folderPath.trim());
                long folderSize = FileUtils.folderSize(path.toFile());

                if (MAX_BOOKS_FOLDER_SIZE < folderSize)
                    throw new RuntimeException("Folder size is greater than 500 MB ! Current size: " + ((folderSize / 1024) / 1024) + " MB");

                int pdfFilesCount = Math.toIntExact(Files.walk(path)
                        .filter(p -> p.toFile().isFile())
                        .filter(p -> p.toFile().getName().toLowerCase().endsWith(".pdf"))
                        .count());

                if (pdfFilesCount > 0) {
                    BookCategory other = categoryService.findByNameEn(DEFAULT_CATEGORY);

                    Map<String, Integer> importedBooks = new HashMap<>();
                    Map<String, Integer> notImportedBooks = new HashMap<>();

                    int maxIterations = (pdfFilesCount / PDF_FILES_PER_ITERATION) + 1;
                    for (int skip, i = 0; i < maxIterations ; i++) {
                        skip = i * PDF_FILES_PER_ITERATION;
                        List<File> filePart = FileUtils.getPdfFiles(path, skip,PDF_FILES_PER_ITERATION);
                        for (int j = 0; j < filePart.size(); j++) {
                            progress = (100 * (j + skip + 1) / pdfFilesCount);
                            File file = filePart.get(j);
                            String bookTitle = file.getName().replaceAll(".pdf", "");
                            List<Book> sameBooks = bookService.findByTitleAndFileSize(bookTitle, Math.toIntExact(file.length()));
                            if (CollectionUtils.isEmpty(sameBooks)) {
                                Book book = Utils.convertFileToBook(file, bookTitle, other);
                                bookService.saveAndFlush(book);
                                importedBooks.put(file.getName(), Math.toIntExact(file.length()));
                            } else
                                notImportedBooks.put(file.getName(), Math.toIntExact(file.length()));
                        }
                    }
                    model.addAttribute("importedBooks", importedBooks);
                    model.addAttribute("notimported", notImportedBooks);
                } else
                    throw new RuntimeException("There are no PDF files in folder !");

                inProgress = false;
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException("An error occurred while importing the file ! Please check folder path !");
            } finally {
                inProgress = false;
                progress = 0;
            }

        } else
            model.addAttribute("alreadyStarted", true);

        return "admin/books-folder-content";
    }

    @RequestMapping(value = "/get-books-upload-progress", method = RequestMethod.POST)
    @ResponseBody
    public Integer getProgress() {
        if (inProgress)
            return progress;
        else
            return null;
    }

}

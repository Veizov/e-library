package bg.tu.sofia.controller;

import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.BookCategory;
import bg.tu.sofia.service.BookCategoryService;
import bg.tu.sofia.service.BookService;
import bg.tu.sofia.validator.BookValidator;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
@RequestMapping("/admin")
public class CreateBookController {
    private static final Integer MAX_BOOK_COVER_FILE_SIZE = 1048576; //1 MB
    private static final Integer MIN_BYTE_ARRAY_LENGTH = 1;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private BookCategoryService categoryService;

    @RequestMapping(value = "/create-book", method = RequestMethod.GET)
    public String createBook(HttpServletRequest request, Model model) {
        if (!model.containsAttribute("book")) {
            request.getSession().setAttribute("coverImage", new Blobs());
            request.getSession().setAttribute("bookFile", new Blobs());
            model.addAttribute("book", new Book());
        }

        List<BookCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/create-book";
    }

    @RequestMapping(value = "/create-book", method = RequestMethod.POST)
    public String createBook(RedirectAttributes redirectAttributes, HttpServletRequest request, @ModelAttribute Book book, BindingResult bindingResult,
                             @RequestParam("imageFile") MultipartFile imageFileRequest,
                             @RequestParam("bookFile") MultipartFile bookFileRequest) throws IOException {

        Blobs coverImage = (Blobs) request.getSession().getAttribute("coverImage");
        fillBlob(imageFileRequest, coverImage);
        book.setCover(coverImage);

        Blobs bookFile = (Blobs) request.getSession().getAttribute("bookFile");
        fillBlob(bookFileRequest, bookFile);
        book.setFile(bookFile);

        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.book", bindingResult);
            redirectAttributes.addFlashAttribute("book", book);
            return "redirect:/admin/create-book";
        } else {
            byte[] bookCoverContent = book.getCover().getContent();
            if (null == bookCoverContent || MIN_BYTE_ARRAY_LENGTH > bookCoverContent.length)
                book.setCover(null);

            bookService.save(book);
            request.getSession().removeAttribute("coverImage");
            request.getSession().removeAttribute("bookFile");
            return "redirect:/admin/menu";
        }
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

}

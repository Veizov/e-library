package bg.tu.sofia.controller;

import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.service.BookCategoryService;
import bg.tu.sofia.service.BookService;
import bg.tu.sofia.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
public class PublicController {
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer DEFAULT_PAGE = 1;
    private static final String DEFAULT_SORT_ORDER = "ASC";

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true, 10));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String register(Model model) {

        return "public/home";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String searchBooks(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("searchBookFilter", new SearchBookFilter());

        List<Book> books = bookService.findBooks(null, DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
        int total = bookService.countBooks(null);

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", books);
        model.addAttribute("booksTotal", total);
        model.addAttribute("page", DEFAULT_PAGE);
        model.addAttribute("pageSize", DEFAULT_PAGE_SIZE);
        return "public/search-book";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(Model model) {

        return "public/feedback";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(Model model) {

        return "public/contacts";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {

        return "public/profile";
    }

    @RequestMapping(value = "/book-list-filter", method = RequestMethod.POST)
    public String getBookListFiltered(
            Model model,
            HttpServletRequest request,
            SearchBookFilter filter,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer page,
            @RequestParam(value = "isFilter", required = false) Boolean isFilter) {

        if (page == null)
            page = DEFAULT_PAGE;
        if (pageSize == null)
            pageSize = DEFAULT_PAGE_SIZE;
        if (StringUtils.isEmpty(filter.getSortOrder()))
            filter.setSortOrder(DEFAULT_SORT_ORDER);

        if (null == isFilter || !isFilter)
            request.getSession().setAttribute("searchBookFilter", filter);

        SearchBookFilter sessionFilter = (SearchBookFilter) request.getSession().getAttribute("searchBookFilter");

        List<Book> books = bookService.findBooks(sessionFilter, page, pageSize);
        int total = bookService.countBooks(sessionFilter);

        model.addAttribute("books", books);
        model.addAttribute("booksTotal", total);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortOrder", filter.getSortOrder());
        return "public/search-book-table";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete-book", method = RequestMethod.POST)
    public String deleteBook(HttpServletRequest request, Model model,
                             @RequestParam("page") Integer page,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("bookID") Integer bookID) {
        Book book = bookService.findById(bookID);
        if (Objects.nonNull(book))
            bookService.delete(book);

        SearchBookFilter sessionFilter = (SearchBookFilter) request.getSession().getAttribute("searchBookFilter");
        return getBookListFiltered(model, request, sessionFilter, pageSize, page, true);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String feedback(Model model, @PathVariable("id") Integer id) {
        Book book = bookService.findById(id);
        model.addAttribute(book);
        return "public/view-book";
    }

}

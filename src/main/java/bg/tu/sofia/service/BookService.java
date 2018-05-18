package bg.tu.sofia.service;


import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    Book saveAndFlush(Book book);

    List<Book> findAll();

    List<Book> findBooks(SearchBookFilter filter, Integer page, Integer pageSize);

    int countBooks(SearchBookFilter filter);

    List<Book> findByTitleAndFileSize(String title, Integer fileSize);

    Book findById(Integer id);

    void delete(Book book);
}

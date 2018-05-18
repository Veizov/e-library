package bg.tu.sofia.service.impl;


import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.repository.BookRepository;
import bg.tu.sofia.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book saveAndFlush(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooks(SearchBookFilter filter, Integer page, Integer pageSize) {
        return bookRepository.findBooks(filter, page, pageSize);
    }

    @Override
    public int countBooks(SearchBookFilter filter) {
        return bookRepository.countBooks(filter);
    }

    @Override
    public List<Book> findByTitleAndFileSize(String title, Integer fileSize) {
        if (StringUtils.isEmpty(title) || null == fileSize)
            return null;
        return bookRepository.findByTitleAndFileSize(title, fileSize);
    }

    @Override
    public Book findById(Integer id) {
        if (null == id)
            return null;
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Book book) {
        if(Objects.nonNull(book))
            bookRepository.delete(book);
    }
}

package bg.tu.sofia.service.impl;


import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.model.Role;
import bg.tu.sofia.model.User;
import bg.tu.sofia.repository.BookRepository;
import bg.tu.sofia.repository.RoleRepository;
import bg.tu.sofia.repository.UserRepository;
import bg.tu.sofia.service.BookService;
import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
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
}

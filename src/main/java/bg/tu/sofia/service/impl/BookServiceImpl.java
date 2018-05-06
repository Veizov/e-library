package bg.tu.sofia.service.impl;


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

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
       return bookRepository.save(book);
    }
}

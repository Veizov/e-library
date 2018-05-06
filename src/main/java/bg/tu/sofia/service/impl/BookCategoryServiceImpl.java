package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.BookCategory;
import bg.tu.sofia.repository.BookCategoryRepository;
import bg.tu.sofia.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private BookCategoryRepository categoryRepository;

    @Override
    public BookCategory save(BookCategory book) {
       return categoryRepository.save(book);
    }

    @Override
    public List<BookCategory> findAll() {
        return categoryRepository.findAll();
    }
}

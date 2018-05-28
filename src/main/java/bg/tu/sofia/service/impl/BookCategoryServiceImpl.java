package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.BookCategory;
import bg.tu.sofia.repository.BookCategoryRepository;
import bg.tu.sofia.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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

    @Override
    public BookCategory findByNameEn(String nameEn) {
        if(StringUtils.isEmpty(nameEn))
            return null;
        return categoryRepository.findByNameEn(nameEn);
    }

    @Override
    @Transactional
    public void deleteBookCategory(Integer id) {
        if(Objects.nonNull(id))
            categoryRepository.deleteBookCategory(id);
    }
}

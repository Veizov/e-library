package bg.tu.sofia.service;

import bg.tu.sofia.model.BookCategory;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookCategoryService {

    BookCategory save(BookCategory book);

    List<BookCategory> findAll();

    BookCategory findByNameEn(String nameEn);

    void deleteBookCategory(Integer id);

}

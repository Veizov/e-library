package bg.tu.sofia.service;

import bg.tu.sofia.model.BookCategory;

import java.util.List;

public interface BookCategoryService {

    BookCategory save(BookCategory book);

    List<BookCategory> findAll();
}

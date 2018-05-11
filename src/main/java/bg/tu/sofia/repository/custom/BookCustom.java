package bg.tu.sofia.repository.custom;

import bg.tu.sofia.filter.SearchBookFilter;
import bg.tu.sofia.model.Book;

import java.util.List;

public interface BookCustom {

    List<Book> findBooks(SearchBookFilter filter, Integer page, Integer pageSize);

    int countBooks(SearchBookFilter filter);

}

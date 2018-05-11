package bg.tu.sofia.repository;

import bg.tu.sofia.model.Book;
import bg.tu.sofia.repository.custom.BookCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>, BookCustom {

}

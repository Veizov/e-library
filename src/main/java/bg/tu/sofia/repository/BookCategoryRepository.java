package bg.tu.sofia.repository;

import bg.tu.sofia.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

    BookCategory findByNameEn(String nameEn);
}

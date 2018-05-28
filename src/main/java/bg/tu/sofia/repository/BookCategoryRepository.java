package bg.tu.sofia.repository;

import bg.tu.sofia.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

    BookCategory findByNameEn(String nameEn);

    @Modifying
    @Query(value = "DELETE FROM book_category WHERE id = :categoryID", nativeQuery = true)
    void deleteBookCategory(@Param("categoryID") Integer id);
}

package bg.tu.sofia.repository;

import bg.tu.sofia.model.Book;
import bg.tu.sofia.repository.custom.BookCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, BookCustom {

    @Query(value = "SELECT * FROM public.book e JOIN public.blobs b ON e.file = b.id\n" +
            " WHERE e.title = :title AND b.filesize = :fileSize", nativeQuery = true)
    List<Book> findByTitleAndFileSize(@Param("title") String title, @Param("fileSize") Integer fileSize);

}

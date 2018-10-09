package bg.tu.sofia.repository;

import bg.tu.sofia.model.BookCategory;
import bg.tu.sofia.model.FeedbackMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedbackMessageRepository extends JpaRepository<FeedbackMessage, Integer> {

}

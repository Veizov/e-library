package bg.tu.sofia.repository;

import bg.tu.sofia.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, String> {

}

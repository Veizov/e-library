package bg.tu.sofia.repository;

import bg.tu.sofia.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser_Id(Integer id);
}

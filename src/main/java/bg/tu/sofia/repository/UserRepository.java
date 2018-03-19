package bg.tu.sofia.repository;

import bg.tu.sofia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	 User findByEmail(String email);

}

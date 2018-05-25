package bg.tu.sofia.repository;

import bg.tu.sofia.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM user_role WHERE user_id = :userID AND role_id = :roleID", nativeQuery = true)
    void removeRoleFromUser(@Param("roleID") Integer role, @Param("userID") Integer user);

    @Modifying
    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUES (:userID,:roleID)", nativeQuery = true)
    void addRoleToUser(@Param("roleID") Integer role, @Param("userID") Integer user);

}

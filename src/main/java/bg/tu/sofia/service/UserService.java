package bg.tu.sofia.service;


import bg.tu.sofia.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    User saveNewUser(User user);

    List<User> findAll();

    User findById(Integer id);

    void createVerificationToken(User user, String token);

    User save (User user);

    User findByEmailAndActive(String email, Boolean active);

}

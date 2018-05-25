package bg.tu.sofia.service;


import bg.tu.sofia.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    List<User> findAll();

    User findById(Integer id);

}

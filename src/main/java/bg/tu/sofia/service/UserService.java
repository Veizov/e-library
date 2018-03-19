package bg.tu.sofia.service;


import bg.tu.sofia.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}

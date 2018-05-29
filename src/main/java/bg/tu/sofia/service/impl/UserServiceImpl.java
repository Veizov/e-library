package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.Role;
import bg.tu.sofia.model.User;
import bg.tu.sofia.model.VerificationToken;
import bg.tu.sofia.repository.RoleRepository;
import bg.tu.sofia.repository.UserRepository;
import bg.tu.sofia.service.UserService;
import bg.tu.sofia.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(false);
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        User saved = userRepository.save(user);
        return saved;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenService.save(myToken);
    }

    @Override
    public User save(User user) {
        if (Objects.isNull(user))
            return null;
		return userRepository.save(user);
    }

    @Override
    public User findByEmailAndActive(String email, Boolean active) {
        if(StringUtils.isEmpty(email))
            return null;
        return userRepository.findByEmailAndActive(email,active);
    }

}

package bg.tu.sofia.service.impl;


import bg.tu.sofia.model.Role;
import bg.tu.sofia.model.User;
import bg.tu.sofia.repository.RoleRepository;
import bg.tu.sofia.repository.UserRepository;
import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByName("ADMIN");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
		userRepository.save(user);
	}

}

package bg.tu.sofia.service.impl;


import bg.tu.sofia.model.Role;
import bg.tu.sofia.repository.RoleRepository;
import bg.tu.sofia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void removeRoleFromUser(Integer role, Integer user) {
        if (Objects.nonNull(role) && Objects.nonNull(user))
            roleRepository.removeRoleFromUser(role, user);
    }

    @Override
    @Transactional
    public void addRoleToUser(Integer role, Integer user) {
        if (Objects.nonNull(role) && Objects.nonNull(user))
            roleRepository.addRoleToUser(role, user);
    }
}

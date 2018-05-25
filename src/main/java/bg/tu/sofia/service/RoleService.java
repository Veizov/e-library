package bg.tu.sofia.service;


import bg.tu.sofia.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void removeRoleFromUser(Integer role,Integer user);

    void addRoleToUser(Integer role,Integer user);
}

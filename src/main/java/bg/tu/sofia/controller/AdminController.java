package bg.tu.sofia.controller;

import bg.tu.sofia.model.Role;
import bg.tu.sofia.model.User;
import bg.tu.sofia.service.RoleService;
import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view() {
        return "admin/view";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getFname() + " " + user.getLname() + " (" + user.getEmail() + ")");
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        return "admin/menu";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String roles(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/roles";
    }


    @RequestMapping(value = "/get-roles", method = RequestMethod.POST)
    public String getRolesPerUser(Model model, @RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        if(Objects.nonNull(user)){
            Set<Role> userRoles = user.getRoles();

            List<Role> roles = roleService.findAll();
            roles.forEach(role -> {
                boolean contains = userRoles.contains(role);
                if (contains) {
                    role.setContainByUser(true);
                }
            });
            model.addAttribute("roles", roles);
            model.addAttribute("user", user);
        }
        return "admin/roles_table";
    }

    @RequestMapping(value = "/delete-role-from-user", method = RequestMethod.POST)
    public String deleteRoleFromUser(Model model, @RequestParam("user") Integer user, @RequestParam("role") Integer role) {
        User userById = userService.findById(user);
        if(Objects.isNull(userById))
            throw new RuntimeException("User with ID = "+user+" is NULL !");

        roleService.removeRoleFromUser(role,user);
        return getRolesPerUser(model,userById.getEmail());
    }


    @RequestMapping(value = "/add-role-to-user", method = RequestMethod.POST)
    public String addRoleToUser(Model model, @RequestParam("user") Integer user, @RequestParam("role") Integer role) {
        User userById = userService.findById(user);
        if(Objects.isNull(userById))
            throw new RuntimeException("User with ID = "+user+" is NULL !");

        roleService.addRoleToUser(role,user);
        return getRolesPerUser(model,userById.getEmail());
    }

}

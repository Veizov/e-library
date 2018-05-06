package bg.tu.sofia.controller;

import bg.tu.sofia.model.User;
import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

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

}

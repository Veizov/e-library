package bg.tu.sofia.controller;

import bg.tu.sofia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by denislav.veizov on 30.1.2018 г..
 */
@Controller
public class PublicController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String register(Model model) {

        return "public/view";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {

        return "public/search";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(Model model) {

        return "public/feedback";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(Model model) {

        return "public/contacts";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {

        return "public/profile";
    }
}

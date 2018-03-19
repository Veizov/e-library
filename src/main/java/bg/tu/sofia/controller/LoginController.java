package bg.tu.sofia.controller;

import bg.tu.sofia.model.User;
import bg.tu.sofia.service.UserService;
import bg.tu.sofia.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //VALIDATOR
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error)
            model.addAttribute("error", true);
        return "public/login";
    }


    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String access(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "public/access-denied";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, User user) {
        if (user == null)
            model.addAttribute("user", new User());
        else
            model.addAttribute("user", user);

        return "public/register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @ModelAttribute User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return registration(model, user);
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        }
        return "public/register";
    }

}

package bg.tu.sofia.controller;

import bg.tu.sofia.verification.OnRegistrationCompleteEvent;
import bg.tu.sofia.model.User;
import bg.tu.sofia.model.VerificationToken;
import bg.tu.sofia.service.UserService;
import bg.tu.sofia.service.VerificationTokenService;
import bg.tu.sofia.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //VALIDATOR
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private MessageSource messageSource;

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
    public String createNewUser(Model model, HttpServletRequest request, @ModelAttribute User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return registration(model, user);
        } else {
            User newUser = userService.saveNewUser(user);

            String appUrl = request.getRequestURL().toString();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newUser, LocaleContextHolder.getLocale(), appUrl));

            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        }
        return "public/register-success";
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public String confirmRegistration (WebRequest request, Model model, @RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messageSource.getMessage("auth.message.invalidToken", null, LocaleContextHolder.getLocale());
            model.addAttribute("message", message);
            return "public/bad-user";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messageSource.getMessage("auth.message.expired", null, LocaleContextHolder.getLocale());
            model.addAttribute("message", messageValue);
            return "public/bad-user";
        }

        user.setActive(true);
        userService.save(user);

        return "public/confirm-success";
    }

}

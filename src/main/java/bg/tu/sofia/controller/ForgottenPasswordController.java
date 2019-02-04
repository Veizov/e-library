package bg.tu.sofia.controller;

import bg.tu.sofia.model.ForgottenPassword;
import bg.tu.sofia.model.User;
import bg.tu.sofia.service.ForgottenPasswordService;
import bg.tu.sofia.service.UserService;
import bg.tu.sofia.verification.ForgottenPasswordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Objects;

@Controller
@RequestMapping("/forgotten-password")
public class ForgottenPasswordController {
    private static Logger LOG = LoggerFactory.getLogger(ForgottenPasswordController.class);

    @Autowired
    private ForgottenPasswordService forgottenPasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String forgotPasswordGET(Model model, HttpServletRequest request) {

        return "public/forgotten-password";
    }

    @PostMapping
    public String forgotPasswordPOST(Model model, HttpServletRequest request, @RequestParam String email, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(email)) {
            redirectAttributes.addFlashAttribute("emptyEmailValidation", true);
            return "redirect:/forgotten-password";
        }

        User user = userService.findByEmailAndActive(email, true);
        if (Objects.isNull(user)) {
            redirectAttributes.addFlashAttribute("emptyUserValidation", true);
            return "redirect:/forgotten-password";
        }

        String appUrl = request.getRequestURL().toString();
        eventPublisher.publishEvent(new ForgottenPasswordEvent(user, LocaleContextHolder.getLocale(), appUrl));
        model.addAttribute("sendEmailSuccessFully",true);
        return "public/forgotten-password";
    }

    @GetMapping(value = "/change-password")
    public String changePasswordGET(Model model, @RequestParam String token, RedirectAttributes redirectAttributes) {
        ForgottenPassword forgottenPassword = forgottenPasswordService.findByToken(token);

        boolean hasErrors = checkToken(forgottenPassword, redirectAttributes);
        if(hasErrors)
            return "redirect:/forgotten-password";

        User user = userService.findUserByEmail(forgottenPassword.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("token", token);
        return "public/change-password";

    }

    @PostMapping(value = "/change-password")
    public String changePasswordPOST(@RequestParam String password, @RequestParam String confirmPassword, @RequestParam String token, RedirectAttributes redirectAttributes) {
        ForgottenPassword forgottenPassword = forgottenPasswordService.findByToken(token);
        boolean hasErrors = checkToken(forgottenPassword, redirectAttributes);
        if(hasErrors)
            return "redirect:/forgotten-password";

        if(StringUtils.isEmpty(password)){
            redirectAttributes.addFlashAttribute("isEmptyPassword", true);
            redirectAttributes.addAttribute("token",token);
            return "redirect:/forgotten-password/change-password";
        }
        if(!password.equals(confirmPassword)){
            redirectAttributes.addFlashAttribute("isDifferentPasswords", true);
            redirectAttributes.addAttribute("token",token);
            return "redirect:/forgotten-password/change-password";
        }
        User user = userService.findUserByEmail(forgottenPassword.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.save(user);

        redirectAttributes.addFlashAttribute("changePasswordSuccess", true);
        return "redirect:/login";
    }

    private boolean checkToken(ForgottenPassword forgottenPassword, RedirectAttributes redirectAttributes){
        boolean hasErrors = false;
        if (Objects.isNull(forgottenPassword)) {
            redirectAttributes.addFlashAttribute("isTokenMissing", true);
            hasErrors = true;
        }
        Calendar cal = Calendar.getInstance();
        if ((forgottenPassword.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            redirectAttributes.addFlashAttribute("isExpired", true);
            hasErrors = true;
        }
        return hasErrors;
    }

}

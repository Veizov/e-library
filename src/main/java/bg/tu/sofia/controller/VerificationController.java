package bg.tu.sofia.controller;

import bg.tu.sofia.model.User;
import bg.tu.sofia.model.VerificationToken;
import bg.tu.sofia.service.MailService;
import bg.tu.sofia.service.UserService;
import bg.tu.sofia.service.VerificationTokenService;
import bg.tu.sofia.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/verification")
public class VerificationController {
    private static Logger LOG = LoggerFactory.getLogger(VerificationController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/new-verification-code", method = RequestMethod.GET)
    public String newVerificationCode() {
        return "public/new-ver-code";
    }

    @RequestMapping(value = "/send-verification-code", method = RequestMethod.POST)
    public String sendNewVerificationCode(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                          @RequestParam("email") String email) {
        if (!StringUtils.isEmpty(email)) {
            User user = userService.findByEmailAndActive(email, false);
            if (Objects.nonNull(user)) {
                VerificationToken verificationToken = verificationTokenService.findByUser_Id(user.getId());
                if (Objects.nonNull(verificationToken)) {
                    String newToken = UUID.randomUUID().toString();
                    verificationToken.setExpiryDate(Utils.calculateExpiryDate(VerificationToken.EXPIRATION));
                    verificationToken.setToken(newToken);
                    verificationTokenService.save(verificationToken);

                    String requestURL = Utils.getRequestURL(request);
                    String confirmUrl = requestURL + "/e-library/registration/confirm?token=" + newToken;
                    try {
                        mailService.sendVerificationMail(user.getEmail(), confirmUrl);
                    } catch (MessagingException e) {
                        LOG.error(e.getMessage(), e);
                    }

                    redirectAttributes.addFlashAttribute("success", messageSource.getMessage("text.new.verification.code", null, LocaleContextHolder.getLocale()));
                    return "redirect:/verification/new-verification-code";
                }
            } else
                redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("error.user.verif", null, LocaleContextHolder.getLocale()));
        } else
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("empty.field.valid", null, LocaleContextHolder.getLocale()));

        redirectAttributes.addFlashAttribute("email", email);
        return "redirect:/verification/new-verification-code";
    }
}

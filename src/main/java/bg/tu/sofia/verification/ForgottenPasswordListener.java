package bg.tu.sofia.verification;

import bg.tu.sofia.model.User;
import bg.tu.sofia.service.MailService;
import bg.tu.sofia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.UUID;

@Component
public class ForgottenPasswordListener implements ApplicationListener<ForgottenPasswordEvent> {
    private static Logger LOG = LoggerFactory.getLogger(ForgottenPasswordListener.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Override
    public void onApplicationEvent(ForgottenPasswordEvent event) {
        this.changePassword(event);

    }
    private void changePassword(ForgottenPasswordEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createForgottenPasswordToken(user.getEmail(), token);
        try {
            String changePasswordUrl = event.getAppUrl() + "/change-password?token=" + token;
            String recipientAddress = user.getEmail();
            mailService.sendChangePasswordMail(recipientAddress,changePasswordUrl);
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
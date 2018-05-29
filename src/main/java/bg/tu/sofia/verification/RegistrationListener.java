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
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private static Logger LOG = LoggerFactory.getLogger(RegistrationListener.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        try {
            String confirmationUrl = event.getAppUrl() + "/confirm?token=" + token;
            String recipientAddress = user.getEmail();
            mailService.sendVerificationMail(recipientAddress,confirmationUrl);
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
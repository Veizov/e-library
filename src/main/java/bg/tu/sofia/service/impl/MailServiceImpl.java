package bg.tu.sofia.service.impl;

import bg.tu.sofia.property.MailPropertyAccess;
import bg.tu.sofia.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MessageSource messageSource;
    private final MailPropertyAccess mailPropertyAccess;
    private final JavaMailSender mailSender;

    public void sendMail(String recipientAddress, String text, String subject, boolean isHtml) throws MessagingException {
        if (isDisabled())
            return;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        helper.setTo(recipientAddress);
        helper.setText(text, isHtml);
        helper.setSubject(subject);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendVerificationMail(String recipientAddress, String url) throws MessagingException {
        Locale locale = LocaleContextHolder.getLocale();

        String subject = messageSource.getMessage("text.registration.comfirmation", null, locale);
        String message = messageSource.getMessage("text.success.registration", null, locale);
        String linkText = messageSource.getMessage("label.here", null, locale);
        String text = message + " <a href=" + url + ">" + linkText + "</a>";

        sendMail(recipientAddress,text,subject,true);
    }

    @Override
    public void sendChangePasswordMail(String recipientAddress, String url) throws MessagingException {
        Locale locale = LocaleContextHolder.getLocale();

        String subject = messageSource.getMessage("text.change.password.title", null, locale);
        String message = messageSource.getMessage("text.change.password.body", null, locale);
        String linkText = messageSource.getMessage("label.here", null, locale);
        String text = message + " <a href=" + url + ">" + linkText + "</a>";

        sendMail(recipientAddress,text,subject,true);
    }

    private boolean isDisabled() {
        return !isEmailSendingEnabled();
    }

    private boolean isEmailSendingEnabled() {
        Boolean enabled = mailPropertyAccess.getMailSendEmails();
        return Objects.nonNull(enabled) && enabled;
    }
}

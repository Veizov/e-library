package bg.tu.sofia.service.impl;

import bg.tu.sofia.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public JavaMailSender mailSender;

    public void sendMail(String recipientAddress, String text, String subject, boolean isHtml) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
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
}

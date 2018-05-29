package bg.tu.sofia.service;

import javax.mail.MessagingException;

public interface MailService {

    void sendMail(String recipientAddress, String text, String subject, boolean isHtml) throws MessagingException;

    void sendVerificationMail(String recipientAddress, String url) throws MessagingException;
}

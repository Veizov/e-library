package bg.tu.sofia.config;

import bg.tu.sofia.property.MailPropertyAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailSenderConfig {

    private final MailPropertyAccess propertyAccess;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = getJavaMailSender();

        Properties props = mailSender.getJavaMailProperties();
        String transportProtocol = propertyAccess.getMailTransportProtocol();
        if (Objects.nonNull(transportProtocol)) {
            props.put("mail.transport.protocol", transportProtocol);
        }

        Boolean smtpAuth = propertyAccess.getMailSmtpAuth();
        if (Objects.nonNull(smtpAuth)) {
            props.put("mail.smtp.auth", smtpAuth);
        }

        Boolean smtpStarttlsEnable = propertyAccess.getMailSmtpStarttlsEnable();
        if (Objects.nonNull(smtpStarttlsEnable)) {
            props.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
        }

        Boolean smtpSslEnable = propertyAccess.getMailSmtpSslEnable();
        if (Objects.nonNull(smtpSslEnable)) {
            props.put("mail.smtp.ssl.enable", smtpSslEnable);
        }

        Boolean enableDebug = propertyAccess.getMailDebug();
        if (Objects.nonNull(smtpStarttlsEnable)) {
            props.put("mail.debug", enableDebug);
        }
        return mailSender;
    }

    private JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        String mailHost = propertyAccess.getMailHost();
        if (Objects.nonNull(mailHost)) {
            mailSender.setHost(mailHost);
        }

        Integer mailPort = propertyAccess.getMailPort();
        if (Objects.nonNull(mailHost)) {
            mailSender.setPort(mailPort);
        }

        String mailUsername = propertyAccess.getMailUsername();
        if (Objects.nonNull(mailUsername)) {
            mailSender.setUsername(mailUsername);
        }

        String mailPassowrd = propertyAccess.getMailPassword();
        if (Objects.nonNull(mailPassowrd)) {
            mailSender.setPassword(mailPassowrd);
        }
        return mailSender;
    }
}

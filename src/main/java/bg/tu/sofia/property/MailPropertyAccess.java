
package bg.tu.sofia.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailPropertyAccess {

    @Value("${elibrary.mail.host:#{null}}")
    private String mailHost;

    @Value("${elibrary.mail.port:#{null}}")
    private Integer mailPort;

    @Value("${elibrary.mail.username:#{null}}")
    private String mailUsername;

    @Value("${elibrary.mail.password:#{null}}")
    private String mailPassword;

    @Value("${elibrary.mail.from-title:#{null}}")
    private String mailFrom;

    @Value("${elibrary.mail.from-email:#{null}}")
    private String mailFromEmail;

    @Value("${elibrary.mail.transport-protocol:#{null}}")
    private String mailTransportProtocol;

    @Value("${elibrary.mail.smtp-auth:#{null}}")
    private Boolean mailSmtpAuth;

    @Value("${elibrary.mail.smtp-starttls-enable:#{null}}")
    private Boolean mailSmtpStarttlsEnable;

    @Value("${elibrary.mail.smtp-ssl-enable:#{null}}")
    private Boolean mailSmtpSslEnable;

    @Value("${elibrary.mail.debug:#{false}}")
    private Boolean mailDebug;

    @Value("${elibrary.mail.send-emails:#{false}}")
    private Boolean mailSendEmails;
}

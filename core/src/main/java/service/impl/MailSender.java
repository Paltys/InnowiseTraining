package service.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
@NoArgsConstructor
@Data
public class MailSender {

    public void sendMail(String email, String subj, String message) {

        String SEND_USER = "paltys01@gmail.com";
        String SEND_PASS = "7053974ddR";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SEND_USER);
        props.put("mail.smtps.auth", "true");

        Session session = Session.getDefaultInstance(props);

        try {
            Transport transport = session.getTransport();
            transport.connect("smtp.gmail.com", 465, SEND_USER, SEND_PASS);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSubject(subj);
            mimeMessage.setText(message);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSentDate(new Date());
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

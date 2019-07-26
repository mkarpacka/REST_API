package com.example.comarch.services;

import com.example.comarch.entities.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@NoArgsConstructor
public class SendEmailServiceImpl implements SendEmailService{

    @Override
    public void sendConfirmingTransferEmail(String firstAccountNumber, String secondAccountNumber, String email) {
        String to = email;

        String from = "magkarp997@gmail.com";
        final String username = "magkarp997";
        final String password = "birbmemes<3";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Transfer");

            message.setText("Money transfer from " + firstAccountNumber + " to " + secondAccountNumber + " has been made.");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

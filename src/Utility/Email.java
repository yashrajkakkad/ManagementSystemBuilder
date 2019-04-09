package Utility;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
//    private static String sender;

    private static final String USERNAME = "mansystembuilder";
    private static final String PASSWORD = "m@nbui1d3r";
//    private static final int REGISTRATION = 0;
//    private static final int LOGIN = 1;
//Not needed as of now!
    
    private static void sendEmail(String to, String subject, String body) throws AddressException, MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USERNAME);
        props.put("mail.smtp.port", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(USERNAME));
            InternetAddress toAddress = new InternetAddress(to);
//            for (int i = 0; i < to.length; i++) {
//                toAddress[i] = new InternetAddress(to[i]);
//            }
//            for (int i = 0; i < toAddress.length; i++) {
//                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//            }
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, USERNAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}

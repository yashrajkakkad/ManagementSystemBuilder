package Utility;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
//    private static String sender;

    private static final String USERNAME = "mansystembuilder";
    private static final String PASSWORD = "m@nbui1d3r";
    private static final int registration = 0;
    private static final int login = 1;

    public static void sendEmail(String email, int trigger) {
        if (trigger == registration) {

        } else if (trigger == login) {

        }
    }

    private static void sendFromGMail(String from, String pass, String[] to,
            String subject, String body) throws AddressException, MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.port", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        } catch(AddressException ae)  {
            ae.printStackTrace();
        }
        catch(MessagingException me) {
            me.printStackTrace();
        }        
    }

}

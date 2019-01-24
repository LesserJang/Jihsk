package com.jih10157.Jihsk.util.Mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.util.Plugin.ConfigLoader;
import org.bukkit.scheduler.BukkitRunnable;


public class SSLEmailSender {
    private String username;
    private String password;
    private Properties mailProps;

    public SSLEmailSender() {
        this.username = ConfigLoader.emailUsername;
        this.password = ConfigLoader.emailPassword;

        this.mailProps = new Properties();
        this.mailProps.put("mail.smtp.auth", ConfigLoader.emailPasswordRequired);
        this.mailProps.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        this.mailProps.put("mail.smtp.host", ConfigLoader.emailServerHost);
        this.mailProps.put("mail.smtp.port", ConfigLoader.emailPort);
        this.mailProps.put("mail.smtp.socketFactory.port", ConfigLoader.emailPort);

        this.mailProps.put("mail.smtp.timeout", 10000);
        this.mailProps.put("mail.smtp.connectiontimeout", 10000);
    }

    public void send(final String subject, final String text, final String address) {
        new BukkitRunnable() {
            public void run() {
                Session session = Session.getDefaultInstance(SSLEmailSender.this.mailProps,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(SSLEmailSender.this.username, SSLEmailSender.this.password);
                            } } );
                try {
                    session.setDebug(false);
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(SSLEmailSender.this.username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
                    message.setSubject(subject);
                    message.setText(text);
                    Transport transport = session.getTransport("smtp");
                    transport.connect(ConfigLoader.emailServerHost, ConfigLoader.emailPort, SSLEmailSender.this.username, SSLEmailSender.this.password);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}

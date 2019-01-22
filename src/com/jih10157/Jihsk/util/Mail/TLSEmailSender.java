package com.jih10157.Jihsk.util.Mail;

import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jih10157.Jihsk.Main;
import com.jih10157.Jihsk.util.Plugin.ConfigLoader;
import org.bukkit.scheduler.BukkitRunnable;

public class TLSEmailSender {
    private String username;
    private String password;
    private Properties mailProps;

    public TLSEmailSender() {
        this.username = ConfigLoader.emailUsername;
        this.password = ConfigLoader.emailPassword;

        this.mailProps = new Properties();
        this.mailProps.put("mail.smtp.auth", ConfigLoader.emailPasswordRequired);
        this.mailProps.put("mail.smtp.starttls.enable", "true");
        this.mailProps.put("mail.smtp.host", ConfigLoader.emailServerHost);
        this.mailProps.put("mail.smtp.port", ConfigLoader.emailPort);
        this.mailProps.put("mail.smtp.timeout", 3000);
        this.mailProps.put("mail.smtp.connectiontimeout", 3000);
    }

    public void send(String subject, String text, String address) {
        new BukkitRunnable() {
            public void run() {
                try {
                    Session session = Session.getInstance(TLSEmailSender.this.mailProps, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(TLSEmailSender.this.username, TLSEmailSender.this.password);
                        }
                    });
                    session.setDebug(false);
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(TLSEmailSender.this.username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
                    message.setSubject(subject);
                    MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
                    mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                    mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
                    CommandMap.setDefaultCommandMap(mc);
                    message.setText(text);
                    Transport.send(message);
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}

package eu.fugiczek.maturita.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //SMTP server configuration is needed here
       /* Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(port);
        mailSender.setProtocol("SMTP");
        mailSender.setUsername("");
        mailSender.setPassword("");*/
        return mailSender;
    }
	
}

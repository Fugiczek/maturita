package eu.fugiczek.maturita.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import eu.fugiczek.maturita.domain.ReplyWrapper;

@Service
public class Mailer {

	@Autowired
    private JavaMailSender sender;
	
	public void send(ReplyWrapper reply) {
		send(reply, null);
	}
	
	public void send(ReplyWrapper reply, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(reply.getFrom());
		message.setSubject("Respond to your inquiry");
		message.setText(reply.getText());
		sender.send(message);
	}
	
}

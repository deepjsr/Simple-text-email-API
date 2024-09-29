package com.example.EmailServiceAPI.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmaiServiceAPIService {
	public boolean sendEmail(String subject, String message, String to) {
		String from = "ajsxx67@gmail.com";

		boolean f = false;
//	    Variable for gmail HOST
		String host = "smtp.gmail.com";
//	        system properties
		Properties properties = System.getProperties();
		System.out.println("Properties: " + properties);

//	        setting important info to properties obj

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//	        Get the session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ajsxx67@gmail.com", "wzez qhmy rtfs sbsl");
			}

		});
		session.setDebug(true);
//	        construct a message
		MimeMessage m = new MimeMessage(session);

		try {
//	        set From
			m.setFrom(from);

//	            Add recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//	            adding subject
			m.setSubject(subject);

//	            adding text message
			m.setText(message);

//	            send using transport class
			Transport.send(m);
			System.out.println("Sent successfully......");
			f = true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return f;
	}
}

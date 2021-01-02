package com.pooagr.sending_email;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {
		System.out.println("preparing to send message...");
		String message = "Hello User, This is a testing Message from program - sending_email. Thank you";
		String subject = "codingPractice : sending_email";
		String to = "pooagr18@gmail.com";
		String from = "pooagr9@gmail.com";

//        sendEmail(message, subject, to, from);
		sendAttachment(message, subject, to, from);

	}

	private static void sendAttachment(String message, String subject, String to, String from) {
		// variable for gmail host
		String host = "smtp.gmail.com";

		// get system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

//				Setting important information to properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//				Get session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("youremail@gmail.com", "Password");
			}
		});

		session.setDebug(true);

//				compose the message
		MimeMessage msg = new MimeMessage(session);
		MimeMultipart m = new MimeMultipart();
		MimeBodyPart mimeText = new MimeBodyPart();
		MimeBodyPart mimeFile = new MimeBodyPart();

		String filePath = "C:\\Users\\Pooja\\Desktop\\davv_img1.jpg";
		try {
			msg.setFrom(from);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);

			// Preparing multimedia file
			mimeText.setText(message);
			File file = new File(filePath);
			mimeFile.attachFile(file);
			m.addBodyPart(mimeFile);
			m.addBodyPart(mimeText);

			msg.setContent(m);
			Transport.send(msg);
			System.out.println("message sent successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void sendEmail(String message, String subject, String to, String from) {
		// variable for gmail host
		String host = "smtp.gmail.com";

		// get system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

//		Setting important information to properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//		Get session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("youremail@gmail.com", "password");
			}
		});

		session.setDebug(true);

//		compose the message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(from);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);

//			send
			Transport.send(msg);
			System.out.println("message send successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

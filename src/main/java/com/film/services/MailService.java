package com.film.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("$(spring.mail.username)")
	private String fromMail;
	
	public void sendEmail(String mail, String subject, String message) {
		MimeMessage mimeMsg = javaMailSender.createMimeMessage();
		try {
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMsg, true);
            mailMessage.setTo(mail);
            mailMessage.setSubject(subject);
            mailMessage.setText(message, true); // Set HTML content to true
            javaMailSender.send(mimeMsg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public String getHtmlContentFromFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Could not read HTML file from classpath: " + filePath, e);
        }
    }
}

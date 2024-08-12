package com.weha.email.services;

import com.weha.email.dtos.EmailMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

@Service
public class EmailService {

    @Value("$spring.mail.username")
    private String from;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sentEmail(EmailMessageRequest request) {
        try {
            MimeMessagePreparator message = mimeMessage -> {
                String template = readEmailTemplate("welcome.html");
                template = template.replace("${P_USER}", "Weha");
                template = template.replace("${P_MESSAGE}", request.message());
                template = template.replace("${P_WEBSITE}", "https://www.google.com");

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom(from + "@gmail.com");
                helper.setTo(request.to());
                helper.setSubject(request.subject());
                helper.setText(template, true);
            };
            mailSender.send(message);
            return "Sent successfully " + LocalTime.now();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String readEmailTemplate(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:email/" + filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }
}

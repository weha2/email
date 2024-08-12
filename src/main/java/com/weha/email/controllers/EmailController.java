package com.weha.email.controllers;

import com.weha.email.dtos.EmailMessageRequest;
import com.weha.email.services.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@Tag(name = "Email", description = "APIs for testing sent email.")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sentEmail(@RequestBody EmailMessageRequest request) {
        return ResponseEntity.ok(emailService.sentEmail(request));
    }
}

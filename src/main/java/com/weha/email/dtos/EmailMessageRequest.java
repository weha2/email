package com.weha.email.dtos;

public record EmailMessageRequest(
        String to,
        String subject,
        String message
) {
}

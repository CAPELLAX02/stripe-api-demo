package com.capellax.stripe;

public record PaymentResponse(
        String status,
        String message,
        String sessionId,
        String sessionUrl
) {
}

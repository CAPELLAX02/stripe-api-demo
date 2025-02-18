package com.capellax.stripe;

public record PaymentRequest(
        Long amount,
        Long quantity,
        String name,
        String currency
) {
}

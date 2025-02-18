package com.capellax.stripe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest paymentRequest
    ) {
        PaymentResponse response = stripeService.checkoutProducts(paymentRequest);
        return ResponseEntity.ok(response);
    }

}

package com.capellax.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentResponse checkoutProducts(PaymentRequest paymentRequest) {
        var productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(paymentRequest.name())
                .build();

        var priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(paymentRequest.currency() != null ? paymentRequest.currency() : "USD")
                .setUnitAmount(paymentRequest.amount())
                .setProductData(productData)
                .build();

        var lineItem = SessionCreateParams.LineItem.builder()
                .setQuantity(paymentRequest.quantity())
                .setPriceData(priceData)
                .build();

        var params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(lineItem)
                .build();

        Session session = null;

        try {
            session = Session.create(params);
        }
        catch (StripeException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new PaymentResponse(
                "Success",
                "Payment session created",
                session.getId(),
                session.getUrl()
        );
    }

}

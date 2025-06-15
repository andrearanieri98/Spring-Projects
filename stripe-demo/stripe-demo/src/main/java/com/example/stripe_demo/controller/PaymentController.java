package com.example.stripe_demo.controller;

import com.example.stripe_demo.dto.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${stripe.public-key}")
    private String publicKey;

    @GetMapping("/")
    public String home(Model model) {
        logger.info("Accesso alla pagina di pagamento");
        model.addAttribute("stripePublicKey", publicKey);
        return "index";
    }

    @PostMapping("/create-payment-intent")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequest request) {
        logger.info("Richiesta create-payment-intent ricevuta: amount={}, currency={}",
                request.getAmount(), request.getCurrency());

        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(request.getAmount())
                    .setCurrency(request.getCurrency())
                    .build();

            logger.debug("Creazione PaymentIntent con params: {}", params);
            PaymentIntent intent = PaymentIntent.create(params);

            logger.info("PaymentIntent creato con id={} e status={}", intent.getId(), intent.getStatus());

            Map<String, String> responseData = new HashMap<>();
            responseData.put("clientSecret", intent.getClientSecret());

            return ResponseEntity.ok(responseData);

        } catch (StripeException e) {
            logger.error("Errore StripeException durante createPaymentIntent: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }
}

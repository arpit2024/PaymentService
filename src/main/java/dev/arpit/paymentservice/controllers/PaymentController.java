package dev.arpit.paymentservice.controllers;

import com.razorpay.RazorpayException;
import dev.arpit.paymentservice.dtos.CreatePaymentLinkRequestDto;
import dev.arpit.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto request) throws RazorpayException {
    //so to create a payment link we need Order Id
    //then in this Req we will basically be calling service

        String link=paymentService.createPaymentLink(request.getOrderId());
        return link;
    }

    //so whenever a payment link is created, even if the payment is successful or failed,
    // razorpay will send a webhook event to the application/URL
    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, String> webhookEvent) {
        System.out.println(webhookEvent);
    }

}

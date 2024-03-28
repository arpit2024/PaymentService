package dev.arpit.paymentservice.services;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;


public interface PaymentService {

    String createPaymentLink(String orderId) throws RazorpayException; //this will return the payment link

    String getPaymentStatus(String paymentId); //this will return the payment status

}
/*
So behind the scenes, the Payment API from the controller will invoke the Payment service.
The Payment service, in turn, will interact with the payment gateway, which typically constitutes
a third-party service.

However, to ensure flexibility and avoid being tied to a single payment gateway, it's advisable
to create a service interface.
This interface will allow for seamless integration with different payment gateways, thereby
enhancing scalability and adaptability.
 */
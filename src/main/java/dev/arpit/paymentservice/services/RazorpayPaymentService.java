package dev.arpit.paymentservice.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorpayPaymentService implements PaymentService {

    private RazorpayClient razorpayClient;
    public RazorpayPaymentService(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(String orderId) throws RazorpayException {
        //This below code is taken from a Razorpay github-payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);//1000 paise = 10 rupees
        //reason for 1000 is floating point values will not save the exact value which leads to error in payment
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 15*60*1000);
        // 15 minutes from now in milliseconds -> System.currentTimeMillis() = current time in milliseconds
        //time zone is not considered here, it is in UTC time zone i.e. epoch time in milliseconds
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for order no "+orderId);

        // Order order = orderService.getOrderDetails(orderId)
        // String cutomerName = order.getUser().getName()
        // String contact = order.getUser().getMobile()

        JSONObject customer = new JSONObject();
        customer.put("name","+918618300867");
        customer.put("contact","Arpit Vajrangi");
        customer.put("email","arpitvajrangi99@gmail.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        //alerting the user through sms and email
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("Order Items","1. iPhone 15 Pro Max");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://arpitv.dev/");
        //callback url is the url where the payment status will be sent like after payment is done it will directly go to this url/to app confirmation page
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        //return payment.toString();
        return payment.get("short_url");
    }
    @Override
    public String getPaymentStatus(String paymentId) {
        // Go to DB
        // check if the status of payment in DB
        // if no:
        //  call the payment gateway to check status ofd payment
        //   update that status into its own DB
        // return the status
        return null;
    }
}

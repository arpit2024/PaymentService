package dev.arpit.paymentservice.configs;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    //values are taken from application.properties file which set in the environment variables

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient(
                razorpayKeyId,
                razorpaySecret
        );
    }
}
/*So my rzorpay key and secrets are generated from razorpay Dashboard
But technically they should not be in code be in code base or Application.properties file
they should be in environment variables or some secure place
*/


//Following is the code snippet from the file: src/main/java/dev/arpit/paymentservice/dtos/CreatePaymentLinkRequestDto.java
//https://github.com/razorpay/razorpay-java
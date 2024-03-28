# APIs Required in Payment Service:

1. **Create Payment Link:**
    - Endpoint: `/create-payment-link`
    - Description: Allows users to request a payment link for a specific order.
    - Method: POST
    - Parameters:
        - Order ID
        - Amount
        - Callback URL (Optional)
    - Response:
        - Payment link URL

2. **Get Payment Status:**
    - Endpoint: `/get-payment-status`
    - Description: Retrieves the current status of a payment.
    - Method: GET
    - Parameters:
        - Order ID
    - Response:
        - Payment status (e.g., pending, success, failed)

3. **Handle WebHookEvent:**
    - Endpoint: `/webhook`
    - Description: Receives events from the payment gateway (e.g., payment success, failure).
    - Method: POST
    - Parameters:
        - Event type
        - Payment details
    - Action:
        - Update payment status in the Order Service

# Flow Overview:

1. **User -> OrderService:**
    - User sends a request to create an order.
    - Order is created and saved in the database.

    
2. **User -> PaymentService -> OrderService:**
    - User requests a payment link from the Payment Service.
    - Payment Service communicates with the Order Service to retrieve order details.
    - Payment link for the order is created.


3. **User -> Payment Link:**
    - User clicks on the payment link and proceeds with payment.
    - Payment is made, and payment status is updated in the Payment Service.
    - User gets redirected to the callback URL.


4. **CallBack -> PaymentService -> OrderService:**
    - Callback URL makes a call to the Payment Service to check the status of the payment.
    - Payment status is updated in the Order Service.


5. **PaymentGateway -> PaymentService webhook URL:**
    - Payment gateway calls the Payment Service webhook URL.
    - Payment Service handles webhook events (e.g., payment success, failure).

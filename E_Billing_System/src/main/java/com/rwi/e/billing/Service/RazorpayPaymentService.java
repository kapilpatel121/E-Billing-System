package com.rwi.e.billing.Service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayPaymentService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    
    public Map<String, String> createOrder( Double amount) {
        try {
            RazorpayClient razorpay = new RazorpayClient(razorpayKey, razorpaySecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1); // Auto capture

            Order order = razorpay.orders.create(orderRequest);
            Map<String, String> response = new HashMap<>();
            response.put("orderId", order.get("id"));
            response.put("key", razorpayKey);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error creating order", e);
        }
    }
}

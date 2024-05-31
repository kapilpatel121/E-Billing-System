package com.rwi.e.billing.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rwi.e.billing.dto.Customer;

@Service
public class QRCodeGenerator {

    public String generateQRCode(Customer customer, Map<String, Integer> productMap) throws Exception {
        int width = 150;
        int height = 150;

    	// Get the current date
    	LocalDate currentDate = LocalDate.now();

    	// Get the current time
    	LocalTime currentTime = LocalTime.now();

    	// Define formats for date and time
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    	// Format the current date and time using the defined formats
    	String formattedDate = currentDate.format(dateFormatter);
    	String formattedTime = currentTime.format(timeFormatter);

        StringBuilder data = new StringBuilder();
        data.append("Date :- "+formattedDate)
        .append("\nTime :- "+formattedTime)
        .append("\nCustomer name: ").append(customer.getCustomerName())
            .append("\nContact Number: ").append(customer.getContactNumber())
            .append("\nEmail: ").append(customer.getEmail())
            .append("\nPayment Mode: ").append(customer.getPaymentMode())
            .append("\n\nProducts:\n");

        // Create table-like structure for product map
        data.append("Product Name").append("    ").append("Quantity").append("\n");
        data.append("---------------------------------\n");

        for (Map.Entry<String, Integer> entry : productMap.entrySet()) {
            data.append(entry.getKey()).append("    ").append(entry.getValue()).append("\n");
        }
        data.append("---------------------------------\n")
        .append("Total Amount: ").append(customer.getTotalAmount());
        
        String filePath = "E:\\Ebilling\\QRCodes\\" + customer.getId() + "QR.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(data.toString(), BarcodeFormat.QR_CODE, width, height, hintMap);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("QR Code generated successfully.");
        return "QR Code generated successfully.";
    }
}

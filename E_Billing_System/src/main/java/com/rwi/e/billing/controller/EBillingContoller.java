package com.rwi.e.billing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rwi.e.billing.Service.EmailPdfService;
import com.rwi.e.billing.Service.IBillingService;
import com.rwi.e.billing.Service.PdfService;
import com.rwi.e.billing.Service.Product_Service;
import com.rwi.e.billing.Service.QRCodeGenerator;
import com.rwi.e.billing.dto.Customer;
import com.rwi.e.billing.dto.Product_Dto;
import com.rwi.e.billing.dto.RequestData;

@RestController
@RequestMapping("/api/customers")
public class EBillingContoller {

	@Autowired
	private IBillingService billService;

	@Autowired
	private Product_Service productService;

	@Autowired
	private EmailPdfService mailService;

	@Autowired
	private PdfService pdfService;

	@Autowired
	private QRCodeGenerator qrGenerator;

	
	@PostMapping("/addBillAndSendProductList")
	public ResponseEntity<String> addBillAndSendProductList(@RequestBody RequestData requestData) {
		try {
			// Extract data from the request body
			Map<String, Integer> productMap = requestData.getProductMap();
			Map<String, String> formData = requestData.getFormData();
			Double totalPrice = requestData.getTotalPrice();

			// Handle the productList received from the client
			if (productMap != null && formData != null && totalPrice != null) {
				// Update stock 
				String result = productService.updateStock(productMap);
				//System.out.println(productMap);

				
				Customer customer = new Customer(formData.get("customer_name"), formData.get("contact_number"),
						formData.get("email"), formData.get("payment_mode"), totalPrice);

				customer.setNoOfProduct(productMap.size());
				// Create bill and send response
				int id = billService.SaveBillInfo(customer);
				//generate the pdf
				customer.setId(id);
				String qr= qrGenerator.generateQRCode(customer,productMap);
				System.out.println("QR code Generated successfully");
				String filePath = pdfService.createPdf(customer, productMap);
				customer.setPdfPath(filePath);
				//update the bill info set generated pdf path to the bill
				billService.UpdateBill(id, customer);
				//send the bill as pdf and visit again message
				mailService.sendVisitAgain(customer.getEmail(), filePath);
				return ResponseEntity.ok("Bill and product list processed successfully");
			} else {
				// Return an error response if the productList or form data is empty or null
				System.out.println("khali hai ");
				return ResponseEntity.badRequest().body("Product list or form data is empty or invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error processing bill and product list");
		}
	}
	
	@GetMapping("/openPdf") 
	public ResponseEntity<String> OpenPdf(@RequestParam  String pdfPath ) {
		System.out.println( "file to open"+ pdfPath);
	pdfService.openPdfFile(pdfPath);
	 return new ResponseEntity<>("OK", HttpStatus.OK);	
	}
	@GetMapping("/getAllBills")
	public ResponseEntity<List<Customer>> getAllProducts() {
		ResponseEntity<?> productsResponseEntity = billService.getAllBills();

		// Cast the ResponseEntity<?> to ResponseEntity<List<Product_Entity>>
		ResponseEntity<List<Customer>> bills = (ResponseEntity<List<Customer>>) productsResponseEntity;

		return bills;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/getProduct")
	public String getProductNames() {
		try {
			// Fetch product names from the repository
			List<String> productNames = productService.getItemNames();

			// Convert the list of product names to JSON
			ObjectMapper mapper = new ObjectMapper();
			String jsonProductNames = mapper.writeValueAsString(productNames);

			// Return the JSON response
			return jsonProductNames;
		} catch (JsonProcessingException e) {
			// Handle JSON processing exception
			e.printStackTrace();
			return "{\"error\": \"An error occurred while processing the JSON response.\"}";
		}
	}

	@GetMapping("/getProductDetails")
	public ResponseEntity<Product_Dto> getProductDetailsByName(@RequestParam String productName) {
		try {
			//get product details from repository 
			Product_Dto product = productService.getDeatilsByName(productName);
			if (product != null) {
				// Return the  response
				return ResponseEntity.ok(product);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/getDashboardInfo")
	public Map<String, Object> getDashboardData() {
		Map<String, Object> dashboardData = new HashMap<String, Object>();
		dashboardData.put("totalPurchases", 100000.00); // 
		dashboardData.put("totalSales", billService.fetchTotalSells()); //fetch total sell from repository
		dashboardData.put("totalProfit", 30000.00); //
		dashboardData.put("totalCustomers", billService.fetchTotalCustomerCount()); //fetch total customer count from repository
		return dashboardData;
	}

	@GetMapping("/getNavbarInfo")
	public Map<String, Object> getNavbarInfo() {
		Map<String, Object> navbarData = new HashMap<String, Object>();
		navbarData.put("todaysCustomerCount", billService.fetchTodaysCustomerCount()); //fetch todays customer count
		navbarData.put("todaysSell", billService.fetchTodaysSell());  //fetch todays sell
		
		return navbarData;
	}

}

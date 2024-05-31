package com.rwi.e.billing.Service;


import org.springframework.http.ResponseEntity;

import com.rwi.e.billing.dto.Customer;

public interface IBillingService {

	public int SaveBillInfo(Customer cust);
	public String UpdateBill(int id,Customer cust);
	public ResponseEntity<?> getAllBills();
	int fetchTodaysCustomerCount();
	int fetchTotalCustomerCount();
	public Double fetchTotalSells();
	Double fetchTodaysSell(); 
	
	
}

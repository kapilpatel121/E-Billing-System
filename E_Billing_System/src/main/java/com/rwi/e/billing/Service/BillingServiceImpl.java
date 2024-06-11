package com.rwi.e.billing.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rwi.e.billing.Entity.Bill_Entity;
import com.rwi.e.billing.dto.Customer;
import com.rwi.e.billing.repository.IBillRepository;

@Service
public class BillingServiceImpl implements IBillingService {

	@Autowired
	private IBillRepository billRepo;

	
	
	@Override
	public int SaveBillInfo(Customer customer) {
		// save billing info in billing repository
		Bill_Entity entity = new Bill_Entity();
		entity.setName(customer.getCustomerName());
		entity.setContactNo(customer.getContactNumber());
		entity.setEmail(customer.getEmail());
		entity.setPaymentType(customer.getPaymentMode());
		entity.setTotalAmount(customer.getTotalAmount());
		entity.setNoOfProduct(customer.getNoOfProduct());
		// save and return id
		return billRepo.save(entity).getId();
	}

	@Override
	public String UpdateBill(int id, Customer cust) {
		// update billing info set pdf path
		Optional<Bill_Entity> opt = billRepo.findById(id);
		if (opt.isPresent()) {
			Bill_Entity entity = opt.get();
			entity.setPdfPath("E:/Ebilling/Bill" + id + ".pdf");
			billRepo.save(entity);
			return "file Path updated";
		}
		return "file Path not updated";
	}

	// Format the LocalDateTime object
public static String formatDate(LocalDateTime dateTime) {
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");
    return dateTime.format(formatter);
}

	@Override
	public ResponseEntity<?> getAllBills() {
		List<Customer> bill = new ArrayList<>();
		try {
			List<Bill_Entity> entityList = billRepo.getAllBills();
			// Mapping entity objects to DTO objects
			for (Bill_Entity entity : entityList) {
				Customer dto = new Customer();
				dto.setId(entity.getId());
				dto.setCustomerName(entity.getName());
				dto.setContactNumber(entity.getContactNo());
				dto.setEmail(entity.getEmail());
				dto.setNoOfProduct(entity.getNoOfProduct());
				dto.setPaymentMode(entity.getPaymentType());
				dto.setPdfPath(entity.getPdfPath());
				dto.setPurchaseTime(formatDate(entity.getPurchaseTime()));
				dto.setTotalAmount(entity.getTotalAmount());
				dto.setPdfPath(entity.getPdfPath());
				dto.setCustomerName(entity.getName());
				bill.add(dto);
			}
			// Returning success response with the list of DTOs
			return ResponseEntity.status(HttpStatus.OK).body(bill);
		} catch (Exception e) {
			e.printStackTrace();
			// Returning error response
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
	}

	@Override
	public int fetchTodaysCustomerCount() {
		// fetch Todays CustomerCount
		return billRepo.customerCount();
	}

	@Override
	public Double fetchTotalSells() {
		// fetch all over sell
		return billRepo.getTotalSells();
	}

	@Override
	public Double fetchTodaysSell() {
		// fetch todays sell
		return billRepo.getTodaysSells();
	}

	@Override
	public int fetchTotalCustomerCount() {
		// fetch all over customer count
		return billRepo.totalcustomerCount();
	}

	@Override
	public Bill_Entity getBillById(int id) {
		// TODO Auto-generated method stub
		return billRepo.getById(id);
	}

}

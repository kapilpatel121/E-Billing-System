package com.rwi.e.billing.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.rwi.e.billing.Entity.Product_Entity;
import com.rwi.e.billing.dto.Product_Dto;

public interface Product_Service {
	ResponseEntity<?> addProduct(Product_Dto product_Dto);
	ResponseEntity<?> getAllProduct();
	List<String> getItemNames();
	public Integer getUnits(String name);
     ResponseEntity<?>updateProduct(int id, Product_Dto productUpdate);
     ResponseEntity<?> deleteProduct(int id);
	 Product_Entity getProductDeatilsById(int id);
	 Product_Dto getDeatilsByName(String productName);
	 String updateStock(Map<String,Integer> map);
	 
	 public Double fetchTotalPerchase();
	public Double fetchTotalProfit();
	
}


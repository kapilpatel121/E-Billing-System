
package com.rwi.e.billing.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rwi.e.billing.Entity.Product_Entity;
import com.rwi.e.billing.dto.Product_Dto;
import com.rwi.e.billing.repository.Product_Repository;

@Service("productService")
public class Product_Service_Imp implements Product_Service {
	@Autowired
	private Product_Repository product_Repository;

	@Override
	public ResponseEntity<?> addProduct(Product_Dto product_Dto) {
		try {
		Optional<Product_Entity>ProductID=product_Repository.findById(product_Dto.getId());
		if(ProductID.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exists");
		}
		Product_Entity addproduct=new Product_Entity();
		addproduct.setName(product_Dto.getName());
			addproduct.setDiscount(product_Dto.getDiscount());
			addproduct.setPrice(product_Dto.getPrice());
			addproduct.setPurchasePrice(product_Dto.getPurchasePrice());
			addproduct.setUnits(product_Dto.getUnits());
			addproduct.setMfd(product_Dto.getMfd());
			addproduct.setExp(product_Dto.getExp());
			addproduct.setGst(product_Dto.getGst());
			addproduct.setCompany_name(product_Dto.getCompany_name());
		product_Repository.save(addproduct);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Product add success");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Getting Some error"+e);
		}
	}
	
	@Override
	// Update Product Method
	public ResponseEntity<?> updateProduct(int id, Product_Dto productUpdate) {
		try {
			Optional<Product_Entity>ProductID=product_Repository.findById(id);
			 if (ProductID.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product id is empty");
	            }
			 Product_Entity UpdateProducts=ProductID.get();
			 UpdateProducts.setName(productUpdate.getName());
			 UpdateProducts.setDiscount(productUpdate.getDiscount());
			 UpdateProducts.setPrice(productUpdate.getPrice());
			 UpdateProducts.setUnits(productUpdate.getUnits());
			 UpdateProducts.setMfd(productUpdate.getMfd());
			 UpdateProducts.setExp(productUpdate.getExp());
			 UpdateProducts.setGst(productUpdate.getGst());
			 UpdateProducts.setCompany_name(productUpdate.getCompany_name());
			 product_Repository.save(UpdateProducts);
//			 product_Repository.deleteById(id);
			 return ResponseEntity.status(HttpStatus.OK).body("Product Update Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is not updated, some error found"+e);
		}
	}
	
	@Override
	public ResponseEntity<?> deleteProduct(int id) {
		try {
			 Optional<Product_Entity> deleteProduct = product_Repository.findById(id);
			 if(deleteProduct.isEmpty()) {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product id is not found ");
			 }
			 product_Repository.deleteById(id);
			 return ResponseEntity.status(HttpStatus.OK).body("Product deleted Successfully ");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is not deleted ,getting some error!!!!! ");
		}
	}
	
	@Override
	public ResponseEntity<List<Product_Dto>> getAllProduct() {
		List<Product_Dto> prod = new ArrayList<>();
		try {
			List<Product_Entity> entityList = product_Repository.findAll();
			// Mapping entity objects to DTO objects
			for (Product_Entity entity : entityList) {
				Product_Dto dto = new Product_Dto();
				BeanUtils.copyProperties(entity, dto);
				prod.add(dto);
			}
			// Returning success response with the list of DTOs
			return ResponseEntity.status(HttpStatus.OK).body(prod);
		} catch (Exception e) {
			e.printStackTrace();
			// Returning error response
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
	}

	@Override
	public List<String> getItemNames() {
		System.out.println("BillingServiceImpl.getItemNames()");
		List<String> productNames = product_Repository.findAllNamesByOrderByNameAsc();
		return productNames;
	}

	@Override
	public Integer getUnits(String name) {
		System.out.println("Product_Service_Imp.getUnits()");
		return product_Repository.findUnitsByName(name);
	}

	
	@Override
	public Product_Entity getProductDeatilsById(int id) {
		System.out.println("Product_Service_Imp.getProductDeatilsById()");
		Optional<Product_Entity> opt = product_Repository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;

	}

	@Override
	public Product_Dto getDeatilsByName(String productName) {
		System.out.println("Product_Service_Imp.getDeatilsByName()");
		 Product_Dto productDetails= new Product_Dto();
		 Optional<Product_Entity> opt=product_Repository.findProductByName(productName);
		 if(opt.isPresent()) {
         BeanUtils.copyProperties(opt.get(), productDetails);
		 }
		return productDetails;
	}
	@Override
	public String updateStock(Map<String, Integer> map) {
		if(map!=null) {
			int newQuantity=0;
			for (Map.Entry<String,Integer> ProductInfo : map.entrySet())  {
	            System.out.println("Key = " + ProductInfo.getKey() + 
	                             ", Value = " + ProductInfo.getValue());
	           try {
				Product_Dto dto= getDeatilsByName(ProductInfo.getKey());
				newQuantity=dto.getUnits()-ProductInfo.getValue();
				System.out.println(dto); 
				dto.setUnits(newQuantity);;
				  System.out.println("after Stock Updation"+dto);
				  Product_Entity product=new Product_Entity();
				  BeanUtils.copyProperties(dto, product);
				  product_Repository.save(product);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
			return "stock Updated";	
		}
		return "stock not Updated";
	}

	@Override
	public Double fetchTotalPerchase() {
	//return product_Repository.getTotalPurchase();
		return null;
	}

	

	@Override
	public Double fetchTotalProfit() {
		/*
		 * Double purchase,sells,profit; purchase=product_Repository.getTotalPurchase();
		 * sells=product_Repository.getTotalSells(); profit=purchase-sells; return
		 * profit;
		 */
		return null;
	}


}
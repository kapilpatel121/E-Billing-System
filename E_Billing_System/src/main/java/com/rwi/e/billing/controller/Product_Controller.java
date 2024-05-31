package com.rwi.e.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rwi.e.billing.Entity.Product_Entity;
import com.rwi.e.billing.Service.Product_Service;
import com.rwi.e.billing.dto.Product_Dto;

@RestController
@RequestMapping("/rwi/ebill/api")
public class Product_Controller {
	@Autowired
	private Product_Service product_Service;
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct( @RequestBody  Product_Dto request){
		return product_Service.addProduct(request);
		
	}
	// Update Product  by id
	@PostMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product_Dto request) {
        return product_Service.updateProduct(id, request);
    }
	
	//delete product by id 
	 @DeleteMapping("/deleteProduct/{id}")
	    public ResponseEntity<?> DeleteProduct1(@PathVariable int id) {
	        return product_Service.deleteProduct(id);
	    }
  
    @GetMapping("/getProductDetails")
    public ResponseEntity<Product_Dto> getProductDetails(@RequestParam String productName) {
        try {
            // Fetch product details based on the product name
        	Product_Dto productDetails = product_Service.getDeatilsByName(productName);
            
           
            // Return the product details in the response
            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
}
   
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product_Entity>> getAllProducts() {
    	//fetch all product from product repository
        ResponseEntity<?> productsResponseEntity = product_Service.getAllProduct();
        
        // Cast the ResponseEntity<?> to ResponseEntity<List<Product_Entity>>
        ResponseEntity<List<Product_Entity>> products = 
            (ResponseEntity<List<Product_Entity>>) productsResponseEntity;

        return products;
    }
    
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product_Entity> getProductById(@PathVariable("id") int id) {
    	//fetch  product from product repository by id
    	Product_Entity product = product_Service.getProductDeatilsById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
   
}

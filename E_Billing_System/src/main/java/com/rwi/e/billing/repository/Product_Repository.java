package com.rwi.e.billing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rwi.e.billing.Entity.Product_Entity;

public interface Product_Repository extends JpaRepository<Product_Entity, Integer> {

	Optional<Product_Entity> findById(int id);
	
	@Query("SELECT name FROM Product_Entity order by name asc")
    List<String> findAllNamesByOrderByNameAsc();

	 @Query("SELECT p.units FROM Product_Entity p WHERE p.name = :productName")
	    Integer findUnitsByName(String productName);

    @Query("SELECT p FROM Product_Entity p WHERE p.name = :productName")
     Optional<Product_Entity> findProductByName(String productName);
     
		/*
		 * @Query("SELECT sum(purchasePrice) from Product_Entity") Double
		 * getTotalPurchase();
		 */
		 
		
     
    
}

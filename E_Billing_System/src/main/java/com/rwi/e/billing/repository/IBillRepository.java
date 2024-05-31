package com.rwi.e.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rwi.e.billing.Entity.Bill_Entity;


@EnableJpaRepositories
public interface IBillRepository extends JpaRepository<Bill_Entity, Integer> {
	
    @Query("SELECT COUNT(*) FROM Bill_Entity  WHERE DATE(purchaseTime) = CURRENT_DATE")
    int customerCount();
    
    @Query("SELECT COUNT(*) FROM Bill_Entity")
    int totalcustomerCount();
    
    @Query("SELECT sum(totalAmount) from Bill_Entity") 
	 Double getTotalSells();
    
    @Query("SELECT sum(totalAmount) from Bill_Entity WHERE DATE(purchaseTime) = CURRENT_DATE") 
	 Double getTodaysSells();
    
    @Query("from Bill_Entity order by id ASC") 
    List<Bill_Entity> getAllBills();
	
}

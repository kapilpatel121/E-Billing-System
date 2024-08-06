package com.rwi.e.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EBillingSystemApplication {
	
	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(EBillingSystemApplication.class, args);
		
	}

}

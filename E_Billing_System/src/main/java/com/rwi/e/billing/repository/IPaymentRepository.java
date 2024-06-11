package com.rwi.e.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rwi.e.billing.Entity.PaymentInfo;

public interface IPaymentRepository extends JpaRepository<PaymentInfo, String> {

}

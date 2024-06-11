package com.rwi.e.billing.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwi.e.billing.Entity.PaymentInfo;
import com.rwi.e.billing.dto.PaymentDTO;
import com.rwi.e.billing.repository.IPaymentRepository;
@Service
public class PaymentServiceImpl implements IPaymentService{

	@Autowired
	private IPaymentRepository repo;
	@Autowired
	private IBillingService billService;
	
	@Override
	public PaymentInfo savePaymentInfo(PaymentDTO paymentDto) {
		PaymentInfo info=new PaymentInfo();
		BeanUtils.copyProperties(paymentDto, info);
		info.setBill(billService.getBillById(paymentDto.getBillId()));
		System.out.println(info);
		return repo.save(info);
	}

}

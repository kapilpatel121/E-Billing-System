package com.rwi.e.billing.Service;

import com.rwi.e.billing.Entity.PaymentInfo;
import com.rwi.e.billing.dto.PaymentDTO;

public interface IPaymentService {

	public PaymentInfo savePaymentInfo(PaymentDTO paymentDto);
}

package com.ankoye.springcloud.service;

import com.ankoye.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public int create(Payment payment) {
        return 1;
    }

    public Payment getPaymentById(Long id) {
        return new Payment(1L, "abc");
    }
}

package com.ankoye.springcloud.service;

import com.ankoye.springcloud.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public Result paymentSQL(Long id) {
        return new Result(444, "fallback");
    }
}

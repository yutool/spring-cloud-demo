package com.ankoye.springcloud.controller;

import com.ankoye.springcloud.entity.Payment;
import com.ankoye.springcloud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_SRV = "http://cloud-provider-payment";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public Result create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_SRV+"/payment/create", payment, Result.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_SRV+"/payment/get/"+id, Result.class);
    }

}

package com.ankoye.springcloud.controller;

import com.ankoye.springcloud.entity.Payment;
import com.ankoye.springcloud.entity.Result;
import com.ankoye.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public Result create(Payment payment) {
        paymentService.create(payment);
        return new Result(0, "success: " + serverPort);
    }

    @GetMapping("/payment/get/{id}")
    public Result getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return new Result(0, "success: " + serverPort, payment);
    }

    /**
     * 测试负载均衡算法
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * 测试feign延时
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}

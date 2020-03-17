package com.ankoye.springcloud.controller;

import com.ankoye.springcloud.entity.Payment;
import com.ankoye.springcloud.entity.Result;
import com.ankoye.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

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
     * 微服务发现
     * @return
     */
    @GetMapping(value = "payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element:" + element);
        }
        // 一个微服务下的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.debug(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + instance.getUri());
        }
        return this.discoveryClient;
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

    /**
     * 链路跟踪
     */
    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin() {
        return "hi,i'am paymentZipkin server fall back,welcome to atguigu,O(∩_∩)O哈哈~";
    }
}

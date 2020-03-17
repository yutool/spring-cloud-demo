package com.ankoye.springcloud.controller;

import com.ankoye.springcloud.entity.Payment;
import com.ankoye.springcloud.entity.Result;
import com.ankoye.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    // public static final String PAYMENT_URL = "http://localhost:8001"; // 单机版
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    // 自定义轮询算法
    @Resource
    private LoadBalancer loadBalancer;

    /**
     * http://localhost/consumer/payment/create?serial=atguigu002
     */
    @GetMapping("/consumer/payment/create")
    public Result create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL +"/payment/create", payment, Result.class);
    }

    /**
     * http://localhost/consumer/payment/get/31
     */
    @GetMapping("/consumer/payment/get/{id}")
    public Result getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL +"/payment/get/"+id, Result.class);
    }

    /**
     * http://localhost/consumer/payment/getForEntity/31
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public Result getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<Result> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, Result.class);
        return entity.getBody();
    }

    /**
     * 自定义路由规则: 轮询
     * http://localhost/consumer/payment/payment/lb
     *
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    /**
     * 链路跟踪 zipkin+sleuth
     * http://localhost/consumer/payment/zipkin
     *
     * @return
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin/", String.class);
    }
}

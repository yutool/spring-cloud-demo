package com.ankoye.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ankoye@qq.com
 * @date 2021/10/17
 */
@FeignClient(value = "order-provider", fallbackFactory = OrderFeignFallbackFactory.class)
public interface OrderFeign {

    @GetMapping("/test")
    Object test();
}

package com.ankoye.springcloud.feign;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * @author ankoye@qq.com
 * @date 2021/10/17
 */
@Component
public class OrderFeignFallbackFactory implements FallbackFactory<OrderFeign> {

    @Override
    public OrderFeign create(Throwable throwable) {
        return new OrderFeign() {
            @Override
            public Object test() {
                if (throwable instanceof FlowException) {
                    return null;
                }
                // ...
                return null;
            }
        };
    }
}

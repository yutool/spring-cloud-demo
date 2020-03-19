package com.ankoye.springcloud.service;

import com.ankoye.springcloud.domain.Order;

public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
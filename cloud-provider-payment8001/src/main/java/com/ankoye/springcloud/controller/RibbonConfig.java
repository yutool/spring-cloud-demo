package com.ankoye.springcloud.controller;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ankoye@qq.com
 * @date 2021/08/29
 */
@Configuration
public class RibbonConfig {

    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }
}

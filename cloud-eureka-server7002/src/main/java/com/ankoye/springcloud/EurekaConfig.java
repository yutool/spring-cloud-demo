package com.ankoye.springcloud;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ankoye@qq.com
 * @date 2021/08/13
 */
@EnableWebSecurity
public class EurekaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        // 开启认证
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}

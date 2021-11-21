package com.ankoye.springcloud.config;

import cn.hutool.http.HttpRequest;
import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @author ankoye@qq.com
 * @date 2021/10/17
 */
public class ExceptionUtil {
    public static SentinelClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution ex, BlockException be) {
        return new SentinelClientHttpResponse("result");
    }
}

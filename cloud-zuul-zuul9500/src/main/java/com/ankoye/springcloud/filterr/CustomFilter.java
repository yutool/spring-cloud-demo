package com.ankoye.springcloud.filterr;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ankoye@qq.com
 * @date 2021/10/01
 */
@Component
public class CustomFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        // 请求结束，不再继续执行
        rc.setSendZuulResponse(false);
        try {
            rc.getResponse().getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

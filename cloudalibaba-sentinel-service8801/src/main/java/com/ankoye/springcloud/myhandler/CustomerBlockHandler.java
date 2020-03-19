package com.ankoye.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ankoye.springcloud.entity.Result;

public class CustomerBlockHandler {

    public static Result handlerException(BlockException exception) {
        return new Result(444, "客户自定义，global handlerException---1");
    }

    public static Result handlerException2(BlockException exception) {
        return new Result(444, "客户自定义，global handlerException---2");
    }
}
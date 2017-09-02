package com.switchvov.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author Switch
 * @Date 2017/9/2
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return FilterType.PRE.type;
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
    public Object run() {
        LOGGER.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext context = RequestContext.getCurrentContext();
        // 走统一异常拦截
        doSomething();
// 自定义异常拦截
//        try {
//            doSomething();
//        } catch (Exception e) {
//            context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            context.set("error.exception", e);
//            context.set("error.message", "存在一些错误...");
//        }
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}

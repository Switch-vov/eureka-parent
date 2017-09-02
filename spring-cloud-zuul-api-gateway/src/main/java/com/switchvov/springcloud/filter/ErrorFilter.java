package com.switchvov.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 错误过滤器，统一的错误处理
 *
 * @Author Switch
 * @Date 2017/9/2
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return FilterType.ERROR.type;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = context.getThrowable();
        LOGGER.error("this is a ErrorFilter:{}", throwable.getCause().getMessage());
        context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        context.set("error.exception", throwable.getCause());
        return null;
    }
}

package com.switchvov.springcloud.processor;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 扩展Zuul核心处理器
 * <p>处理post过滤器的错误</p>
 * <p>在@{@link RequestContext}上下文中添加相应错误字段</p>
 *
 * @Author Switch
 * @Date 2017/9/2
 */
public class PostErrorProcessor extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext context = RequestContext.getCurrentContext();
            context.set("failed.filter", filter);
            throw e;
        }
    }
}

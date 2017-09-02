package com.switchvov.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 错误扩展过滤器，捕获post抛出的异常
 *
 * @Author Switch
 * @Date 2017/9/2
 */
@Component
public class ErrorExtFilter extends SendErrorFilter {

    @Override
    public String filterType() {
        return FilterType.ERROR.type;
    }

    @Override
    public int filterOrder() {
        return 30; // 大于ErrorFilter的值
    }

    @Override
    public boolean shouldFilter() {
        // 判断：仅处理来自post过滤器引起的异常
        RequestContext context = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) context.get("failed.filter");
        if (failedFilter != null && FilterType.POST.type.equals(failedFilter.filterType())) {
            return true;
        }
        return false;
    }
}

package filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.switchvov.springcloud.filter.FilterType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

/**
 *
 * @Author Switch
 * @Date 2017/9/3
 */
class PreFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreFilter.class)

    @Override
    String filterType() {
        return FilterType.PRE.type
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
        LOGGER.info("this is pre filter: Send {} request to {}", request.getMethod(), request.getRequestURL().toString())
    }
}

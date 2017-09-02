package filter.post

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.switchvov.springcloud.filter.FilterType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletResponse

/**
 *
 * @Author Switch
 * @Date 2017/9/3
 */
class PostFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostFilter.class)

    @Override
    String filterType() {
        return FilterType.POST.type
    }

    @Override
    int filterOrder() {
        return 2000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        LOGGER.info("this is a post filter: Receive Response")
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse()
        String contentType = response.getContentType()
        if (contentType.contains("text/html")) {
            response.getOutputStream().print(", I am Switch")
        }
    }
}

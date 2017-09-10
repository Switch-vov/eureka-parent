package com.switchvov.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * trace被调用端
 * Created by Switch on 2017/9/10.
 */
@RestController
public class TraceServiceController {
    private final Logger LOGGER = LoggerFactory.getLogger(TraceServiceController.class);

    @RequestMapping(value = "/trace-2", method = RequestMethod.GET)
    public String trace(HttpServletRequest request) {
        LOGGER.info("===< call trace-2, TraceId={}, SpanId={}, ParentSpanId={}, Sampled={}, Span-Name={} >===",
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"),
                request.getHeader("X-B3-ParentSpanId"), request.getHeader("X-B3-Sampled"),
                request.getHeader("X-Span-Name"));
        return "Trace";
    }

}

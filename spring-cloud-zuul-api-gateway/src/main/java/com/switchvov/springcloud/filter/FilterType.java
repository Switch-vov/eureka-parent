package com.switchvov.springcloud.filter;

/**
 * @Author Switch
 * @Date 2017/9/2
 */
public enum FilterType {
    PRE("pre"),
    ROUTE("route"),
    POST("post"),
    ERROR("error");

    public final String type;

    FilterType(String type) {
        this.type = type;
    }
}

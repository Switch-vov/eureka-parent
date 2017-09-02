package com.switchvov.springcloud.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Switch
 * @Date 2017/9/3
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {
    private String root;
    private Integer interval;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "FilterConfiguration{" +
                "root='" + root + '\'' +
                ", interval=" + interval +
                '}';
    }
}

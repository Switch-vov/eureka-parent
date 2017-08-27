package com.switchvov.springcloud.annotation;

/**
 * 排除包扫描注解
 * 在启动类上加入注解：
 * <p>@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class))</p>
 * 在需要排除的类上加上该注解
 *
 * @Author Switch
 * @Date 2017/8/6
 */
public @interface ExcludeComponentScan {
}

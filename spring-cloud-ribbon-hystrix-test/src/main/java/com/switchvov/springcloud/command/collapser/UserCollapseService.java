package com.switchvov.springcloud.command.collapser;

import com.switchvov.springcloud.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
@Service
public class UserCollapseService {
    @Autowired
    private RestTemplate restTemplate;

    public User find(Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
    }

    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }
}

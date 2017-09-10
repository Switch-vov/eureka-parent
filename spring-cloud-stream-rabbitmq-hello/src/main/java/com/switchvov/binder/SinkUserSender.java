package com.switchvov.binder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchvov.pojo.User;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * @Author Switch
 * @Date 2017/9/10
 */
public class SinkUserSender {
    @Bean
    @InboundChannelAdapter(value = Sink.INPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        return () -> new GenericMessage<>("{\"name\":\"switch\", \"age\":21}");
    }

    // @Transformer(inputChannel = Sink.INPUT, outputChannel = Sink.INPUT)
    // 使用StreamListener注解，不需要配置转换器
    public User transform(String message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(message, User.class);
        return user;
    }
}

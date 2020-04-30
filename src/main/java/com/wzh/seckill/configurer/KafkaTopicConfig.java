package com.wzh.seckill.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wzh
 * @description: com.wzh.seckill.configurer
 * @date:2020/4/30
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private  String bootstrapServers;
}

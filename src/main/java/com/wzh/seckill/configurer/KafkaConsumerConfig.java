package com.wzh.seckill.configurer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wzh
 * @description: com.wzh.seckill.configurer
 * @date:2020/4/26
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    String boostrapServer;
    @Value("${spring.kafka.consumer.group-id}")
    String createOrder;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    String autoOffsetReset;

    @Bean
    public ConsumerFactory consumerFactory(){
        Map<String,Object> props = new HashMap<String,Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,boostrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,createOrder);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

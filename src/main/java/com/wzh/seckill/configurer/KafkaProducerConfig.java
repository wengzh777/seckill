package com.wzh.seckill.configurer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wzh
 * @description: com.wzh.seckill.configurer
 * @date:2020/4/26
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private  String bootstrapServers;

    @Bean
    public ProducerFactory producerFactory(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG ,3); //重试次数
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,10000); //发送超时时间
        props.put(ProducerConfig.ACKS_CONFIG,"all"); //心跳机制：全部集群收到了才算成功
        return new DefaultKafkaProducerFactory(props);
    }

    @Bean
    public KafkaTemplate<String,String> template(){
        return new KafkaTemplate<String,String>(producerFactory());
    }

}

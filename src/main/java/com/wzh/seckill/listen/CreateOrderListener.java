package com.wzh.seckill.listen;

import com.wzh.seckill.constant.KafkaConstant;
import com.wzh.seckill.model.SuccessKilled;
import com.wzh.seckill.service.SuccessKilledService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: wzh
 * @description: com.wzh.seckill.listen
 * @date:2020/4/26
 */
@Component
public class CreateOrderListener {
    @Resource
    SuccessKilledService successKilledService;

    @KafkaListener(topics = KafkaConstant.TOPIC_CREATE_ORDER,group = "create_order")
    public void listen(String message){
        Long secKillId = Long.valueOf(message.split(",")[0]);
        Long userId = Long.valueOf(message.split(",")[1]);
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setState((byte) 0);
        successKilled.setSeckillId(secKillId);
        successKilled.setUserId(userId);
        successKilled.setCreateTime(new Date());
        successKilledService.save(successKilled);
    }
}

package com.wzh.seckill.service.impl;

import com.wzh.seckill.constant.RedisConstant;
import com.wzh.seckill.core.AbstractService;
import com.wzh.seckill.core.Result;
import com.wzh.seckill.core.ResultCode;
import com.wzh.seckill.dao.SeckillMapper;
import com.wzh.seckill.dao.SuccessKilledMapper;
import com.wzh.seckill.model.Seckill;
import com.wzh.seckill.model.SuccessKilled;
import com.wzh.seckill.service.SeckillService;
import com.wzh.seckill.service.SuccessKilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2020/04/15.
 */
@Service
@Transactional
public class SeckillServiceImpl extends AbstractService<Seckill> implements SeckillService {
    @Resource
    private SeckillMapper seckillMapper;
    @Resource
    private SuccessKilledMapper successKilledMapper;
    @Autowired
    private SuccessKilledService successKilledService;
    @Resource(name = "objectRedisTemplate")
    private RedisTemplate<String, Object> objectRedisTemplate;


    /**
     * @description 悲观锁
     * @author wzh
     * @date 2020/4/15 16:52
     */
    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result seckill0(long id, long userId) {
        Seckill seckill;
        SuccessKilled successKilled;
        if ((seckill = seckillMapper.killNow(id)) == null)
            return new Result(ResultCode.FAIL, "秒杀未开始！");
        if (seckill.getNumber() <= 0)
            return new Result(ResultCode.FAIL, "秒杀已结束！");
        if ((successKilled = successKilledMapper.killed(id, userId)) != null)
            return new Result(ResultCode.FAIL, "你已经参与过秒杀活动！");
        //减库存
        int n = seckillMapper.decrNum(id);
        if (n == 1) {
            successKilled = new SuccessKilled();
            successKilled.setState((byte) 0);
            successKilled.setSeckillId(id);
            successKilled.setUserId(userId);
            successKilled.setCreateTime(new Date());
            successKilledService.save(successKilled);
            return new Result(ResultCode.SUCCESS, "秒杀成功！");
        }
        return new Result(ResultCode.FAIL, "秒杀失败，请稍后再试！");
    }

    /**
     * @description 带缓存的悲观锁形式
     * @author wzh
     * @date 2020/4/15 16:52
     */
    @SuppressWarnings({"Duplicates", "UnusedAssignment"})
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result seckill1(long id, long userId) {
        Seckill seckill = (Seckill) objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_KEY + id).get();
        if (seckill == null) {
            seckill = seckillMapper.selectByPrimaryKey(id);
            objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_KEY + id).set(seckill);
            objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_NUM_KEY + id).set(seckill.getNumber());
        }
        Date now = new Date();
        if (!(seckill.getStartTime().before(now) && seckill.getEndTime().after(now)))
            return new Result(ResultCode.FAIL, "秒杀未开始！");
        if ((int)objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_NUM_KEY + id).get() <= 0)
            return new Result(ResultCode.FAIL, "秒杀已结束！");
        if (!objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_SUCCESS_KEY + id + "_" + userId).setIfAbsent(0))
            return new Result(ResultCode.FAIL, "你已经参与过秒杀活动！");

        //减库存
        int i = seckillMapper.decrNum(id);
        if (i > 0) {
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setState((byte) 0);
            successKilled.setSeckillId(id);
            successKilled.setUserId(userId);
            successKilled.setCreateTime(new Date());
            successKilledService.save(successKilled);
            //缓存下单记录
            objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_SUCCESS_KEY + id + "_" + userId).set(userId);
            //减库存
            objectRedisTemplate.boundValueOps(RedisConstant.SECKILL_NUM_KEY + id).increment(-1);
            return new Result(ResultCode.SUCCESS, "秒杀成功！");
        }

        return new Result(ResultCode.SUCCESS, "秒杀失败！");
    }


}

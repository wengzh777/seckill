package com.wzh.seckill.service;
import com.wzh.seckill.core.Result;
import com.wzh.seckill.model.Seckill;
import com.wzh.seckill.core.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by CodeGenerator on 2020/04/15.
 */
public interface SeckillService extends Service<Seckill> {

    Result seckill0(long id,long userId);

    Result seckill1(long id,long userId);

    @SuppressWarnings({"Duplicates", "UnusedAssignment"})
    @Transactional(rollbackFor = Exception.class)
    Result seckill2(long id, long userId);
}

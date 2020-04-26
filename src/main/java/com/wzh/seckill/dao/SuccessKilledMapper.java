package com.wzh.seckill.dao;

import com.wzh.seckill.core.Mapper;
import com.wzh.seckill.model.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SuccessKilledMapper extends Mapper<SuccessKilled> {

    @Select("select * from success_killed where seckill_id = #{seckillId} and user_id = #{userId}")
    SuccessKilled killed(@Param("seckillId") long seckillId,@Param("userId") long userId);
}
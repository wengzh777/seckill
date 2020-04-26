package com.wzh.seckill.dao;

import com.wzh.seckill.core.Mapper;
import com.wzh.seckill.model.Seckill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SeckillMapper extends Mapper<Seckill> {

    @Select("select * from seckill where seckill_id = #{id} and start_time <= sysdate() and end_time >= sysdate()")
    Seckill killNow(@Param("id") long id);

    @Update("update seckill set number = number-1 where seckill_id = #{id} and number > 0")
    int decrNum(@Param("id") long id);

    @Update("update seckill set number = number-1,version = version + 1 where seckill_id = #{id} and number > 0 and version = #{version}")
    int decrNumByVersion(@Param("id") long id,@Param("version") int version);
}
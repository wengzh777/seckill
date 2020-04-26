package com.wzh.seckill.model;

import java.util.Date;
import javax.persistence.*;

public class Seckill {
    /**
     * 商品库存id
     */
    @Id
    @Column(name = "seckill_id")
    private Long seckillId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 库存数量
     */
    private Integer number;

    /**
     * 秒杀开启时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 获取商品库存id
     *
     * @return seckill_id - 商品库存id
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * 设置商品库存id
     *
     * @param seckillId 商品库存id
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取库存数量
     *
     * @return number - 库存数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置库存数量
     *
     * @param number 库存数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取秒杀开启时间
     *
     * @return start_time - 秒杀开启时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置秒杀开启时间
     *
     * @param startTime 秒杀开启时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取秒杀结束时间
     *
     * @return end_time - 秒杀结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置秒杀结束时间
     *
     * @param endTime 秒杀结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
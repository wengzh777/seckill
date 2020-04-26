package com.wzh.seckill.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "success_killed")
public class SuccessKilled {
    /**
     * 秒杀商品id
     */
    @Id
    @Column(name = "seckill_id")
    private Long seckillId;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 状态标示：-1指无效，0指成功，1指已付款
     */
    private Byte state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取秒杀商品id
     *
     * @return seckill_id - 秒杀商品id
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * 设置秒杀商品id
     *
     * @param seckillId 秒杀商品id
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取状态标示：-1指无效，0指成功，1指已付款
     *
     * @return state - 状态标示：-1指无效，0指成功，1指已付款
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置状态标示：-1指无效，0指成功，1指已付款
     *
     * @param state 状态标示：-1指无效，0指成功，1指已付款
     */
    public void setState(Byte state) {
        this.state = state;
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
}
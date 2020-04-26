package com.wzh.seckill.service.impl;

import com.wzh.seckill.dao.SuccessKilledMapper;
import com.wzh.seckill.model.SuccessKilled;
import com.wzh.seckill.service.SuccessKilledService;
import com.wzh.seckill.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/15.
 */
@Service
@Transactional
public class SuccessKilledServiceImpl extends AbstractService<SuccessKilled> implements SuccessKilledService {
    @Resource
    private SuccessKilledMapper successKilledMapper;

}

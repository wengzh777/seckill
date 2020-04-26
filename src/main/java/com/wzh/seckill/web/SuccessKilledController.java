package com.wzh.seckill.web;
import com.wzh.seckill.core.Result;
import com.wzh.seckill.core.ResultGenerator;
import com.wzh.seckill.model.SuccessKilled;
import com.wzh.seckill.service.SuccessKilledService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/04/15.
*/
@RestController
@RequestMapping("/success/killed")
public class SuccessKilledController {
    @Resource
    private SuccessKilledService successKilledService;

    @PostMapping("/add")
    public Result add(SuccessKilled successKilled) {
        successKilledService.save(successKilled);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        successKilledService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SuccessKilled successKilled) {
        successKilledService.update(successKilled);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SuccessKilled successKilled = successKilledService.findById(id);
        return ResultGenerator.genSuccessResult(successKilled);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SuccessKilled> list = successKilledService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

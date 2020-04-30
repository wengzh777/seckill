package com.wzh.seckill.web;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.seckill.core.Result;
import com.wzh.seckill.core.ResultGenerator;
import com.wzh.seckill.model.Seckill;
import com.wzh.seckill.service.SeckillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/04/15.
*/
@RestController
@RequestMapping("/seckill")
public class SeckillController {
    @Resource
    private SeckillService seckillService;

    @PostMapping("/add")
    public Result add(Seckill seckill) {
        seckillService.save(seckill);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        seckillService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Seckill seckill) {
        seckillService.update(seckill);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Seckill seckill = seckillService.findById(id);
        return ResultGenerator.genSuccessResult(seckill);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Seckill> list = seckillService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * @description 悲观锁
     * @author wzh
     * @date 2020/4/26 13:37
     */
    @GetMapping("kill0")
    public Result seckill0(@RequestParam int id,@RequestParam int userId){
        return seckillService.seckill0(id,userId);
    }

    /**
     * @description 带缓存的悲观锁
     * @author wzh
     * @date 2020/4/26 13:37
     */
    @GetMapping("kill1")
    public Result seckill1(@RequestParam int id,@RequestParam int userId) throws InterruptedException {
        return seckillService.seckill1(id,userId);
    }

    /**
     * @description 异步下单秒杀
     * @author wzh
     * @date 2020/4/26 15:58
     */
    @GetMapping("kill2")
    public Result seckill2(@RequestParam int id,@RequestParam int userId) throws InterruptedException {
        return seckillService.seckill2(id,userId);
    }
}

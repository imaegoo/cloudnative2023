package com.nineya.springboot.controller;

import com.nineya.springboot.DTO.ResultDto;
import com.nineya.springboot.entity.LowcodePages;
import com.nineya.springboot.service.LowcodePagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesController
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午5:17
 * 创 建 人：wteng
 */
@RequestMapping("/LowcodePages")
@RestController
public class LowcodePagesController {

    @Autowired
    private LowcodePagesService lowcodePagesService;

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/query")   //监听hello请求
    public ResultDto<LowcodePages> query(String id){
        if (StringUtils.isEmpty(id)){
            log.error("id不可为空");
        }
        log.info("开始查询");
        LowcodePages result =lowcodePagesService.queryById(id);
        log.info("结果为："+result.toString());
        ResultDto resultDto = new ResultDto<LowcodePages>(result);
        resultDto.setCode(200);
        resultDto.setMessage("查询成功");
        return resultDto;
    }

    @PostMapping("/create")
    public ResultDto<LowcodePages> create(@RequestBody LowcodePages lowcodePages){
        lowcodePagesService.insertSelective(lowcodePages);
        ResultDto resultDto = new ResultDto<LowcodePages>(lowcodePages);
        resultDto.setCode(200);
        resultDto.setMessage("创建成功");
        return resultDto;
    }

}

package com.imaegoo.cloudnative2023.controller;

import com.imaegoo.cloudnative2023.DTO.ResultDto;
import com.imaegoo.cloudnative2023.entity.LowcodePages;
import com.imaegoo.cloudnative2023.service.LowcodePagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesController
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午5:17
 * 创 建 人：wteng
 */
@CrossOrigin
@RestController
@RequestMapping("/api/page")
public class LowcodePagesController {

    @Autowired
    private LowcodePagesService lowcodePagesService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get")
    public ResultDto<LowcodePages> query(String pageId) {
        ResultDto<LowcodePages> resultDto = new ResultDto<>();
        if (StringUtils.isEmpty(pageId)) {
            resultDto.setCode(400);
            resultDto.setMessage("pageId 不可为空");
            return resultDto;
        }
        LowcodePages result = lowcodePagesService.queryByPageId(pageId);
        resultDto.setData(result);
        resultDto.setCode(200);
        resultDto.setMessage("查询成功");
        return resultDto;
    }

    @PostMapping("/publish")
    public ResultDto<LowcodePages> create(HttpServletRequest request, @RequestBody LowcodePages lowcodePages) {
        ResultDto<LowcodePages> resultDto = new ResultDto<>();
        if (request.getSession().getAttribute("LowcodeUser") == null) {
            resultDto.setCode(400);
            resultDto.setMessage("请先登录");
            return resultDto;
        }
        lowcodePagesService.insertSelective(lowcodePages);
        resultDto.setCode(200);
        resultDto.setMessage("创建成功");
        return resultDto;
    }
}

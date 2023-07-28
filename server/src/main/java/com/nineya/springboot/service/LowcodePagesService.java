package com.nineya.springboot.service;

import com.nineya.springboot.entity.LowcodePages;

import java.util.List;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesService
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午3:09
 * 创 建 人：wteng
 */

public interface LowcodePagesService {
    LowcodePages queryById(String id);
    int insertSelective(LowcodePages lowcodePages);
}

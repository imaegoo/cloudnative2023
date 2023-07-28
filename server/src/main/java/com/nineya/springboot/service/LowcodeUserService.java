package com.nineya.springboot.service;

import com.nineya.springboot.entity.LowcodeUser;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodeUserService
 * 类 描 述：TODO
 * 创建时间：2023/7/28 上午11:02
 * 创 建 人：wteng
 */

public interface LowcodeUserService {
    LowcodeUser queryByUsername(String username);
    int insert(LowcodeUser lowcodeUser);
}

package com.imaegoo.cloudnative2023.service;

import com.imaegoo.cloudnative2023.entity.LowcodeUser;

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

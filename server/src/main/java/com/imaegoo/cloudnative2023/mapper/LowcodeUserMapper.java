package com.imaegoo.cloudnative2023.mapper;

import com.imaegoo.cloudnative2023.entity.LowcodeUser;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodeUserMapper
 * 类 描 述：TODO
 * 创建时间：2023/7/28 上午11:02
 * 创 建 人：wteng
 */

public interface LowcodeUserMapper {
    LowcodeUser queryByUsername(@Param("username") String username);

    int insert(LowcodeUser lowcodeUser);
}

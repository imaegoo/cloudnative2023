package com.nineya.springboot.mapper;

import com.nineya.springboot.entity.LowcodePages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesMapper
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午5:08
 * 创 建 人：wteng
 */

public interface LowcodePagesMapper {
    LowcodePages queryById(@Param("id") String id);
    int insertSelective(LowcodePages lowcodePages);
}

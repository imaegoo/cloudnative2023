package com.imaegoo.cloudnative2023.mapper;

import com.imaegoo.cloudnative2023.entity.LowcodePages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesMapper
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午5:08
 * 创 建 人：wteng
 */
@Mapper
public interface LowcodePagesMapper {
    LowcodePages queryByPageId(@Param("pageId") String pageId);

    int insertSelective(LowcodePages lowcodePages);
}

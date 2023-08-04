package com.imaegoo.cloudnative2023.service.impl;

import com.imaegoo.cloudnative2023.entity.LowcodePages;
import com.imaegoo.cloudnative2023.mapper.LowcodePagesMapper;
import com.imaegoo.cloudnative2023.service.LowcodePagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesServiceImpl
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午3:09
 * 创 建 人：wteng
 */
@Service
public class LowcodePagesServiceImpl implements LowcodePagesService {
    @Autowired
    private LowcodePagesMapper lowcodePagesMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public LowcodePages queryByPageId(String pageId) {

        LowcodePages result = lowcodePagesMapper.queryByPageId(pageId);
        return result;
    }

    @Override
    public int insertSelective(LowcodePages lowcodePages) {
        Date currentDate = new Date();
        if(lowcodePages.getCreatedTime() == null) {
            Timestamp timestamp = new Timestamp(currentDate.getTime());
            lowcodePages.setCreatedTime(timestamp);
        }

        if(lowcodePages.getUpdatedTime() == null) {
            Timestamp timestamp = new Timestamp(currentDate.getTime());
            lowcodePages.setUpdatedTime(timestamp);
        }
        return lowcodePagesMapper.insertSelective(lowcodePages);
    }
}

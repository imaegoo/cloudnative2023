package com.nineya.springboot.service.impl;

import com.nineya.springboot.entity.LowcodePages;
import com.nineya.springboot.mapper.LowcodePagesMapper;
import com.nineya.springboot.service.LowcodePagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public LowcodePages queryById(String id){

        LowcodePages result = lowcodePagesMapper.queryById(id);
        return result;
    }

    @Override
    public int insertSelective(LowcodePages lowcodePages) {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        lowcodePages.setCreatedTime(timestamp);
        log.info(timestamp.toString());
        lowcodePages.setPageId(UUID.randomUUID().toString());
        int result = lowcodePagesMapper.insertSelective(lowcodePages);
        return 0;
    }
}

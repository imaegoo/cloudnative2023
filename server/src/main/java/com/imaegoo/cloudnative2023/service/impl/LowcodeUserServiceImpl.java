package com.imaegoo.cloudnative2023.service.impl;

import com.imaegoo.cloudnative2023.entity.LowcodeUser;
import com.imaegoo.cloudnative2023.mapper.LowcodeUserMapper;
import com.imaegoo.cloudnative2023.service.LowcodeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodeUserServiceImpl
 * 类 描 述：TODO
 * 创建时间：2023/7/28 上午11:02
 * 创 建 人：wteng
 */
@Service
public class LowcodeUserServiceImpl implements LowcodeUserService {

    @Autowired
    private LowcodeUserMapper lowcodeUserMapper;

    @Override
    public LowcodeUser queryByUsername(String username) {
        LowcodeUser lowcodeUser = lowcodeUserMapper.queryByUsername(username);
        return lowcodeUser;
    }

    @Override
    public int insert(LowcodeUser lowcodeUser) {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        lowcodeUser.setCreatedTime(timestamp);
        return lowcodeUserMapper.insert(lowcodeUser);
    }
}

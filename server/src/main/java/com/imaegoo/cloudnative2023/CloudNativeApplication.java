package com.imaegoo.cloudnative2023;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.imaegoo.cloudnative2023.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
//使用MapperScan批量扫描所有的Mapper接口
@SpringBootApplication(proxyBeanMethods = false)
public class CloudNativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudNativeApplication.class, args);
    }

}

package com.my.lottery.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 */
@Configuration
@MapperScan("com.my.lottery")
public class MyBatisConfig {

}
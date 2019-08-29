/*
 * 文 件 名:  MyBatisConfig.java
 * 版    权:  Copyright 2017 南京云蜗信息技术有限公司,  All rights reserved
 * 描    述:  MyBatis通用框架
 * 版    本： <1.0.0> 
 * 创 建 人:  Huang Hao
 * 创建时间:  2017年12月12日
 
 */
package com.example.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis通用框架
 * @author  Huang Hao
 * @version  [1.0.0, 2017年12月12日]
 * @since  [蜗蜗生活/公用DAO]
 */
@Configuration
@AutoConfigureAfter({MyBatisConfig.class,MyBatisOrderConfig.class})
public class MyBatisMapperScannerConfig
{
    
    @Bean(name="wowoMapperScannerConfigurer")
    @Primary
    public MapperScannerConfigurer wowoMapperScannerConfigurer()
    {
        System.out.println("------wowoMapperScannerConfigurer------");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("wowoSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.example.application.mapper.**");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }


    @Bean(name="orderMapperScannerConfigurer")
    public MapperScannerConfigurer orderMapperScannerConfigurer()
    {
        System.out.println("------orderMapperScannerConfigurer------");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("orderSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.example.order.mapper.**");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}

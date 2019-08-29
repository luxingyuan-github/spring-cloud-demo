/*
 * 文 件 名:  MyBatisConfig.java
 * 版    权:  Copyright 2017 南京云蜗信息技术有限公司,  All rights reserved
 * 描    述:  业务层-通用框架
 * 版    本： <1.0.0> 
 * 创 建 人:  Huang Hao
 * 创建时间:  2017年12月12日
 
 */
package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * 通用框架
 * @author  Huang Hao
 * @version  [1.0.0, 2017年12月12日]
 * @since  [蜗蜗生活/公用DAO]
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.order"},sqlSessionFactoryRef = "orderSqlSessionFactory")
public class MyBatisOrderConfig
{


    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.order.datasource.druid")
    public DruidDataSource secondaryDataSource() {
        System.out.println("------orderDataSource------");
        return new DruidDataSource();
    }





    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DruidDataSource orderDataSource)
    {
        System.out.println("------orderSqlSessionFactory------");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(orderDataSource);

        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);

        // 添加插件
        bean.setPlugins(new Interceptor[] {pageHelper});

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try
        {
            bean.setMapperLocations(resolver.getResources("classpath:com/example/order/*.xml"));
            return bean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
    {
        System.out.println("------orderSqlSessionTemplate------");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "orderTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("orderDataSource") DruidDataSource orderDataSource)
    {
        System.out.println("------orderTransactionManager------");
        return new DataSourceTransactionManager(orderDataSource);
    }
}

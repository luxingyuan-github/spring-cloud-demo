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
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.example.application",sqlSessionFactoryRef = "userSqlSessionFactory")
public class MyBatisConfig
{


    @Bean(name = "userDataSource",destroyMethod = "close",initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.application.datasource.druid")
    public DruidDataSource primaryDataSource() {
        System.out.println("------userDataSource------");
        return new DruidDataSource();
    }

    @Bean(name = "userDataSourceProxy")
    public DataSourceProxy dataSourceProxy(@Qualifier("userDataSource")DruidDataSource druidDataSource) {

        return new DataSourceProxy(druidDataSource);
    }


    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSourceProxy") DataSourceProxy dataSourceProxy)
    {
        System.out.println("------userSqlSessionFactory------");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);
        
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
            bean.setMapperLocations(resolver.getResources("classpath:com/example/application/**/*.xml"));
            return bean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Bean(name = "userSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
    {
        System.out.println("------userSqlSessionTemplate------");
        return new SqlSessionTemplate(sqlSessionFactory);
    }




    @Bean(name =  "userTransactionManager")
    @Primary
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("userDataSource") DataSource userDataSource)
    {
        System.out.println("------userTransactionManager------");
        return new DataSourceTransactionManager(userDataSource);
    }
}

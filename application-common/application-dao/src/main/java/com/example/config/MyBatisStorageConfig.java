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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.storage"},sqlSessionFactoryRef = "storageSqlSessionFactory")
public class MyBatisStorageConfig
{


    @Bean(name = "storageDataSource",destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.storage.datasource.druid")
    public DruidDataSource storageDataSource() {
        System.out.println("------storageDataSource------");
        return new DruidDataSource();
    }


    @Bean(name = "storageDataSourceProxy")
    public DataSourceProxy dataSourceProxy(@Qualifier("storageDataSource")DruidDataSource druidDataSource) {

        return new DataSourceProxy(druidDataSource);
    }




    @Bean(name = "storageSqlSessionFactory")
    public SqlSessionFactory storageSqlSessionFactory(@Qualifier("storageDataSourceProxy") DataSourceProxy dataSourceProxy)
    {
        System.out.println("------storageSqlSessionFactory------");
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
            bean.setMapperLocations(resolver.getResources("classpath:com/example/storage/**/*.xml"));
            return bean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "storageSqlSessionTemplate")
    public SqlSessionTemplate storageSqlSessionTemplate(@Qualifier("storageSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
    {
        System.out.println("------storageSqlSessionTemplate------");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "storageTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("storageDataSource") DruidDataSource storageDataSource)
    {
        System.out.println("------storageTransactionManager------");
        return new DataSourceTransactionManager(storageDataSource);
    }
}

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 * @author lxy
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.example"},exclude = DataSourceAutoConfiguration.class)
public class OrderApplication
{
    public static void main( String[] args )
    {

        SpringApplication.run(OrderApplication.class,args);
    }
}

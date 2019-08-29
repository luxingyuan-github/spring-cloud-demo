package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 * @author lxy
 */
@EnableEurekaServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EurekaServer
{
    public static void main( String[] args )
    {

        SpringApplication.run(EurekaServer.class,args);
    }
}

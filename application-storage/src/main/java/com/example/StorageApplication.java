package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luxingyuan
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.example"},exclude = DataSourceAutoConfiguration.class)
public class StorageApplication {

    public static void main( String[] args )
    {
        SpringApplication.run(StorageApplication.class,args);
    }
}

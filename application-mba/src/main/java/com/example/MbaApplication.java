package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 * @author lxy
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.example"})
public class MbaApplication
{
    public static void main( String[] args )
    {

        SpringApplication.run(MbaApplication.class,args);
    }
}

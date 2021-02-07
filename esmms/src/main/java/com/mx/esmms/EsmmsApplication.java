package com.mx.esmms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.mx.esmms",
        "com.mx.common"})
@MapperScan(basePackages = {
        "com.mx.esmms",
        "com.mx.common"})
@ConfigurationPropertiesScan(basePackages = {
        "com.mx.common"})
public class EsmmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsmmsApplication.class, args);
    }

}

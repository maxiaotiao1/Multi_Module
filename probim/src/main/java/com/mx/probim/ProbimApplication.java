package com.mx.probim;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mx.probim","com.mx.common"})
@ConfigurationPropertiesScan(basePackages = {"com.mx.common"})
public class ProbimApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProbimApplication.class, args);
    }

}

package com.mx.rfid;


import com.mx.rfid.demo.config.InStockHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"com.mx.rfid"})
@MapperScan(basePackages = {
        "com.mx.rfid",
        "com.mx.common"
})
public class RfidApplication {
    public static void main(String[] args) {
        SpringApplication.run(RfidApplication.class, args);
    }
}

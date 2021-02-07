package com.mx.rfid.demo.config;

import com.gg.reader.api.dal.GClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RFIDConfiguration {

    @Bean("GClient")
    GClient getGClient(){
        return new GClient();
    }

    @Bean("Scan")
    Scan getScan(){ return new Scan();
    }

}
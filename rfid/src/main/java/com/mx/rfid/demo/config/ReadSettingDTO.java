package com.mx.rfid.demo.config;

import com.gg.reader.api.protocol.gx.ParamEpcFilter;
import com.gg.reader.api.protocol.gx.ParamEpcReadReserved;
import com.gg.reader.api.protocol.gx.ParamEpcReadTid;
import com.gg.reader.api.protocol.gx.ParamEpcReadUserdata;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "read")
public class ReadSettingDTO {
    //天线端口
    private Long antennaEnable;
    //读取模式（0：单次 1：连续）
    private int inventoryMode;
    //访问区域 1：tid 2：保留区 3：epc
    private int readArea;
    //选择读取参数
    private ParamEpcFilter filter;
    //用户数据区域读取参数
    private ParamEpcReadUserdata readUserdata;
    //保留区读取参数
    private ParamEpcReadReserved readReserved;
    //可选 访问密码
    private String hexPassword;

    private int monzaQtPeek;
    private int monzaT;
    private int emSensor;

    //模式
     private int tidMode;
    //设置长度
     private int tidLen;

    @Bean("ReadSettingDTO")
    public ReadSettingDTO getReadSettingDTO(){
        return new ReadSettingDTO();
    }

    @Bean("ParamEpcReadTid")
    ParamEpcReadTid getParamEpcReadTid(){
        ParamEpcReadTid paramEpcReadTid = new ParamEpcReadTid();
        paramEpcReadTid.setMode(this.getTidMode());
        paramEpcReadTid.setLen(this.getTidLen());
        return paramEpcReadTid;
    }
}

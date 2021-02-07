package com.mx.rfid.demo.service;

import com.gg.reader.api.protocol.gx.MsgAppGetReaderInfo;
import com.gg.reader.api.protocol.gx.MsgBaseGetCapabilities;
import com.gg.reader.api.protocol.gx.MsgBaseGetPower;

public interface RFIDService {
    //连接RFID
    void connectRFID(String port ,Integer timeOut);
    //关闭RFID连接
    void closeConnect();
    //获取连接状态
    Integer getConnectStatus();
    //开启RFID扫描
    void start();
    //停止RFID扫描
    void stop();
    //RFID配置
    void setting(int[] powers);
    //清空扫描结果
    void clear();
    //获取读卡器能力
    MsgBaseGetCapabilities getCapabilities();
    //获取读卡器当前设置
    MsgAppGetReaderInfo getRfidSetting();
    //查询读写器功率
    MsgBaseGetPower getRfidPower();
}

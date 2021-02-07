package com.mx.rfid.demo.service.impl;
import com.gg.reader.api.dal.GClient;
import com.gg.reader.api.dal.HandlerTagEpcLog;
import com.gg.reader.api.dal.HandlerTagEpcOver;
import com.gg.reader.api.protocol.gx.*;
import com.mx.common.globalexception.GlobalException;
import com.mx.rfid.demo.config.*;
import com.mx.rfid.demo.service.RFIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
@Primary
public class RFIDServiceImpl implements RFIDService {

    @Autowired
    private GClient gClient;
    @Autowired
    private Scan scan;
    @Autowired
    private ReadSettingDTO readSettingDTO;
    @Autowired
    private ReadConfiguration readConfiguration;
    @Autowired
    private InStockHandler inStockHandler;
    //0未开启链接，1已开启链接
    private Integer connectStatus;

    //构造方法
    public RFIDServiceImpl() {
        this.connectStatus = 0;
    }

    //获取实例状态
    public Integer getConnectStatus(){
        return this.connectStatus;
    }

    public void setOnTagEpcLog(HandlerTagEpcLog handlerTagEpcLog){
        gClient.onTagEpcLog = handlerTagEpcLog;
    }

    public void setOnTagEpcOver(HandlerTagEpcOver handlerTagEpcOver){
        gClient.onTagEpcOver = handlerTagEpcOver;
    }

    public void setHandlerTagEpcOver(HandlerTagEpcOver handlerTagEpcOver){
        gClient.onTagEpcOver = handlerTagEpcOver;
    }

    //"COM7:115200"2000
    @Override
    public void connectRFID(String port, Integer timeOut) {
        if (connectStatus == 0) {
            //判断链接类型
            if (gClient.openSerial(port, timeOut)) {
                //设置连接状态
                connectStatus = 1;
            } else {
                throw new GlobalException("连接失败");
            }
        }else {
            throw new GlobalException("请勿重复连接");
        }
    }

    @Override
    public void closeConnect() {
        if (connectStatus == 0) {
        }else {
            MsgBaseStop msgBaseStop = new MsgBaseStop();
            gClient.sendSynMsg(msgBaseStop);
            if (gClient.close()) {
                connectStatus = 0;
            }else {
                new GlobalException("com.mx.rfid.demo.service gClient.close");
            }
        }
    }

    //开启扫描
    @Override
    public void start() {
        MsgBaseInventoryEpc msgBaseInventoryEpc = readConfiguration.readConfig(readSettingDTO);
        //未连接
        if (connectStatus == 0){
            throw new GlobalException("未连接");
        }else {
            //已连接
            setOnTagEpcLog(inStockHandler);
            setHandlerTagEpcOver(new InStockHandlerOver());
            stop();
            gClient.sendSynMsg(msgBaseInventoryEpc);
            if (0 == msgBaseInventoryEpc.getRtCode()) {
            } else {
                throw new GlobalException("扫描开启失败");
            }
        }
    }

    @Override
    public void stop() {
        MsgBaseStop msgBaseStop = new MsgBaseStop();
        gClient.sendSynMsg(msgBaseStop);
        if (0 == msgBaseStop.getRtCode()) {
        } else {
        }
    }

    @Override
    public void setting(int[] powers) {
        //判断是否处于连接状态
        if (connectStatus == 1) {
            //停止命令
            stop();
            // 功率配置
            MsgBaseSetPower msgBaseSetPower = new MsgBaseSetPower();
            Hashtable<Integer, Integer> hashtable = new Hashtable<>();
            int i =0;
            for (Integer power: powers) {
                i++;
                if (null == power || 0 > power){
                    continue;
                }
                hashtable.put(i,power);
            }
            System.out.println("设置天线功率："+hashtable.toString());
            msgBaseSetPower.setDicPower(hashtable);
            gClient.sendSynMsg(msgBaseSetPower);
            if (0 == msgBaseSetPower.getRtCode()) {
                System.out.println("功率设置成功");
            } else {
                System.out.println("功率设置失败");
            }
        } else {
            throw new GlobalException("RFID未连接");
        }
    }

    @Override
    public void clear() {
        scan.getStock().clear();
    }

    @Override
    public MsgBaseGetCapabilities getCapabilities() {
        //判断是否处于连接状态
        if (connectStatus == 1) {
            stop();
            MsgBaseGetCapabilities msgBaseGetCapabilities = new MsgBaseGetCapabilities();
            gClient.sendSynMsg(msgBaseGetCapabilities);
            return msgBaseGetCapabilities;
        }else {
            throw new GlobalException("未连接");
        }
    }

    @Override
    public MsgAppGetReaderInfo getRfidSetting() {
        //判断是否处于连接状态
        if ( connectStatus == 1) {
            stop();
            MsgAppGetReaderInfo msgAppGetReaderInfo = new MsgAppGetReaderInfo();
            gClient.sendSynMsg(msgAppGetReaderInfo);
            return msgAppGetReaderInfo;
        }else {
            throw new GlobalException("设置未连接");
        }
    }

    @Override
    public MsgBaseGetPower getRfidPower() {
        if (connectStatus == 1) {
            MsgBaseGetPower msgBaseGetPower = new MsgBaseGetPower();
            gClient.sendSynMsg(msgBaseGetPower);
            if (0 == msgBaseGetPower.getRtCode()) {
                return msgBaseGetPower;
            } else {
                throw new GlobalException("查询功率失败");
            }
        }else {
            throw new GlobalException("连接未开启");
        }
    }

}

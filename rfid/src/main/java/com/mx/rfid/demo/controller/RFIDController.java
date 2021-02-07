package com.mx.rfid.demo.controller;

import com.gg.reader.api.protocol.gx.MsgAppGetReaderInfo;
import com.gg.reader.api.protocol.gx.MsgBaseGetCapabilities;
import com.gg.reader.api.protocol.gx.MsgBaseGetPower;
import com.mx.common.res.ApiResult;
import com.mx.common.res.BaseController;
import com.mx.rfid.datamodule.entity.Label;
import com.mx.rfid.demo.config.Scan;
import com.mx.rfid.demo.service.RFIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping
public class RFIDController extends BaseController {

    @Autowired
    RFIDService rfidService;
    @Autowired
    Scan scan;

    /**
     * 获取扫描结果
     */
    @GetMapping("/getScanResult")
    public ApiResult getScanResult(){
        ArrayList<Label> labels = new ArrayList<>();
        HashMap<String,Label> hashMap = scan.getStock();
        for (String key: hashMap.keySet()) {
            labels.add(hashMap.get(key));
        }
        return success(labels);
    }

    /**
     * 查看读卡器能力
     */
    @GetMapping("/getCapabilities")
    public ApiResult getCapabilities(){
        MsgBaseGetCapabilities capabilities = rfidService.getCapabilities();
        return success(capabilities);
    }

    /**
     *获取读卡器设置
     */
    @GetMapping("/getSettingInfo")
    public ApiResult getSettingInfo(){
        MsgAppGetReaderInfo rfidSetting = rfidService.getRfidSetting();
        return success(rfidSetting);
    }

    @GetMapping("/setting")
    public ApiResult setting(){
        rfidService.setting(new int[]{10,10,10,10});
        return success("设置成功");
    }

    @GetMapping("/start")
    public ApiResult start(){
        rfidService.start();
        return success("扫描开启成功");
    }
    /**
     * 暂停RFID扫描
     */
    @GetMapping("/stop")
    public ApiResult stop(){
        rfidService.stop();
        return success("RFID扫描停止成功");
    }

    /**
     * 获取机器连接状态
     * @return
     */
    @GetMapping("getConnectStatus")
    public ApiResult getConnectStatus(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("connectStatus",rfidService.getConnectStatus());
        return success(hashMap);
    }

    /**
     * 连接RFID
     * @return
     */
    @GetMapping("/connectRFID")
    public ApiResult connectRFID(){
        rfidService.connectRFID("COM7:115200",2000);
        return success("连接成功");
    }

    /**
     * 关闭连接
     */
    @GetMapping("/closeConnect")
    public ApiResult closeConnect(){
        rfidService.closeConnect();
        return success("关闭连接成功");
    }

    /**
     * 清空扫描
     */
    @GetMapping("/clear")
    public ApiResult clear(){
        rfidService.clear();
        return success();
    }

    /**
     * 获取读卡器功率
     */
    @GetMapping("/getRfidPower")
    public ApiResult getRfidPower(){
        MsgBaseGetPower rfidPower = rfidService.getRfidPower();
        return success(rfidPower);
    }

}

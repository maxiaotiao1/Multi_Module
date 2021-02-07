package com.mx.rfid.demo.config;

import com.gg.reader.api.dal.HandlerTagEpcLog;
import com.gg.reader.api.protocol.gx.LogBaseEpcInfo;
import com.mx.rfid.datamodule.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InStockHandler implements HandlerTagEpcLog {
    @Autowired
    Scan scan;

    @Override
    public void log(String s, LogBaseEpcInfo logBaseEpcInfo) {
        // 回调内部如有阻塞，会影响API正常使用
        // 标签回调数量较多，请将标签数据先缓存起来再作业务处理
        if (null != logBaseEpcInfo && 0 == logBaseEpcInfo.getResult()) {
            System.out.println(logBaseEpcInfo);
            Label label = new Label();
            label.setLabelTypeInfo(logBaseEpcInfo.getTid());
            System.out.println(logBaseEpcInfo.getTid());
            scan.getStock().put(logBaseEpcInfo.getTid(),label);
            System.out.println("輸出"+scan.getStock());
        }
    }
}

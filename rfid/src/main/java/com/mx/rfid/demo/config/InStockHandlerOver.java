package com.mx.rfid.demo.config;

import com.gg.reader.api.dal.HandlerTagEpcOver;
import com.gg.reader.api.protocol.gx.LogBaseEpcOver;

public class InStockHandlerOver implements HandlerTagEpcOver {
    @Override
    public void log(String s, LogBaseEpcOver logBaseEpcOver) {
        System.out.println("over log");
    }
}

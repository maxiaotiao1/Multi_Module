package com.mx.mqtt.client;

import com.mx.mqtt.client.MQTTConnect;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName TestCallBack
 * @Author Shaomin
 * @Description TODO
 * @Date 2020-11-20 9:28
 **/
@Component
public class TestCallBack implements MqttCallback {

    @Autowired
    MQTTConnect mqttConnect;

    @PostConstruct
    public void run() {
        System.out.println("执行run方法");
        try {
            MqttClient connect = mqttConnect.getConnect();
            connect.subscribe("test/aaa", 1);
            connect.setCallback(this);
            System.out.println("mqtt启动成功");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        mqttConnect.close();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("连接已断开");
        run();
        System.out.println("重连成功");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

package com.mx.mqtt.client;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

/**
 * @ClassName MQTTConnect
 * @Author Shaomin
 * @Description TODO
 * @Date 2020-11-20 9:09
 **/

@Component
public class MQTTConnect {

    private String HOST="tcp://47.99.130.104:1883";
    MqttClient client;

    public MqttClient getConnect() throws MqttException {
        System.out.println("获取连接");

        String cilentId="mx"+System.currentTimeMillis();
//      if(client==null){
          System.out.println("new client");
          client  = new MqttClient(HOST,cilentId,new MemoryPersistence());
//      }
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(false);
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(false);
        if (!client.isConnected()) {
            client.connect(options);
            System.out.println("连接成功");
        }else {//这里的逻辑是如果连接成功就重新连接
            client.disconnect();
            client.connect(options);
            System.out.println("连接成功");
        }
        return client;
    }


    public void close(){
        try {
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



}

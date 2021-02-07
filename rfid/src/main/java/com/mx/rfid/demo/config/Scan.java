package com.mx.rfid.demo.config;

import com.mx.rfid.datamodule.entity.Label;
import lombok.Data;

import java.util.HashMap;

@Data
public class Scan {
    private HashMap<String, Label> stock = new HashMap<>();
}

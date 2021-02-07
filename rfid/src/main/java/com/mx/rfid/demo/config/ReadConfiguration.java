package com.mx.rfid.demo.config;
import com.gg.reader.api.protocol.gx.EnumG;
import com.gg.reader.api.protocol.gx.MsgBaseInventoryEpc;
import com.gg.reader.api.protocol.gx.ParamEpcReadTid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadConfiguration {
    @Autowired
    ParamEpcReadTid paramEpcReadTid;

    public  MsgBaseInventoryEpc readConfig(ReadSettingDTO readSettingDTO){

        MsgBaseInventoryEpc msgBaseInventoryEpc = new MsgBaseInventoryEpc();
        msgBaseInventoryEpc.setAntennaEnable(EnumG.AntennaNo_4);
        msgBaseInventoryEpc.setInventoryMode(EnumG.InventoryMode_Inventory);

        ParamEpcReadTid tid = new ParamEpcReadTid();
        //设置tid模式（0为自适应最大不超过1字节，1为1字节的长度）
        tid.setMode(EnumG.ParamTidMode_Auto);
        //设置长度
        tid.setLen(6);
        //设置epc数据
        msgBaseInventoryEpc.setReadTid(tid);

//        if (readSettingDTO.getAntennaEnable() != null){
//            msgBaseInventoryEpc.setAntennaEnable(readSettingDTO.getAntennaEnable());
//        }
//            msgBaseInventoryEpc.setInventoryMode(readSettingDTO.getInventoryMode());
//        if (readSettingDTO.getHexPassword() != null){
//            msgBaseInventoryEpc.setHexPassword(readSettingDTO.getHexPassword());
//        }
//
//        switch (readSettingDTO.getReadArea()){
//            case 1:
//                if (paramEpcReadTid != null ){
//                    msgBaseInventoryEpc.setReadTid(paramEpcReadTid);
//                }
//                break;
//            case 2:
//                if (readSettingDTO.getReadUserdata() != null){
//
//                    msgBaseInventoryEpc.setReadUserdata(readSettingDTO.getReadUserdata());
//                }
//                break;
//            case 3:
//                break;
//        }

        return msgBaseInventoryEpc;
    }
}

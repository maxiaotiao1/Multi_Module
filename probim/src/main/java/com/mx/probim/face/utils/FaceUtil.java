package com.mx.probim.face.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mx.probim.face.vo.FindPersonVo;
import com.mx.probim.face.vo.FindRecordVo;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FaceUtil {
    private static String BASE_URL;
    //注册人员
    private static String CREATE_PERSON =  "/person/create";
    //删除人员
    private static String DELETE_PERSON = "/person/delete";
    //查找人员
    private static String FIND_PERSON = "/person/find";
    //注册人脸
    private static String CREATE_FACE = "/face/create";
    //删除人脸
    private static String DELETE_FACE = "/face/delete";
    //设置回调
    private static String SET_CALLBACK = "/setIdentifyCallBack";
    //查询事件
    private static String NEW_FIND_RECORDS = "/newFindRecords";
    //设置门状态
    private static String SET_DOOR_STATUS = "/device/setdoorstate";
    //设置开门延迟时间
    private static String SET_OPEN_DURATION = "/device/setopenduration";
    //添加设备
    private static String ADD_DEVICE = "/device/import";




    @Value("${face.baseUrl}")
    public void setBaseUrl(String baseUrl) {
        FaceUtil.BASE_URL = baseUrl;
    }

    //注册人员
    public Boolean createPerson(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+CREATE_PERSON,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //删除人员
    public Boolean deletePerson(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+DELETE_PERSON,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //查找人员
    public List<FindPersonVo> findPerson(Map<String,String> map) throws IOException {
        List<FindPersonVo> findPersonVoList = new ArrayList<>();
        String params = "?";
        for(String key:map.keySet()){
            params+=key+"="+map.get(key);
        }
        String resultData = sendGetData(BASE_URL+FIND_PERSON+params,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
            return null;
        }
        //取出data中的JSONObject对象数组
        List<JSONObject> objects = jsonObject.getJSONArray("data").toJavaList(JSONObject.class);
        for (JSONObject object: objects) {
            //对每个对象进行组装
            FindPersonVo findPersonVo = new FindPersonVo();
            findPersonVo.setId(object.getString("id"));
            findPersonVo.setIdcardNum(object.getString("idcardNum"));
            findPersonVo.setName(object.getString("name"));
            JSONObject valid = JSON.parseObject(object.getString("valid"));
            findPersonVo.setEnable(valid.getBoolean("enable"));
            findPersonVo.setEndTime(valid.getString("endTime"));
            findPersonVo.setBeginTime(valid.getString("beginTime"));
            findPersonVoList.add(findPersonVo);
        }
        return findPersonVoList;
    }

    //注册人脸
    public Boolean createFace(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+CREATE_FACE,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //删除人脸
    public Boolean deleteFace(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+DELETE_FACE,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //设置回调
    public Boolean setCallback(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+SET_CALLBACK,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //查询事件
    public FindRecordVo newFindRecords(Map<String,String> map) throws IOException {
        String params = "?";
        for(String key:map.keySet()){
            params+=key+"="+map.get(key);
        }
        String resultData = sendGetData(BASE_URL+NEW_FIND_RECORDS+params,"UTF-8");
        FindRecordVo findRecordVo = JSON.parseObject(resultData,FindRecordVo.class);
        return findRecordVo;
    }

    //设置门状态
    public Boolean setDoorStatus(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+SET_DOOR_STATUS,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //设置开门时间延迟
    public Boolean setOpenDuration(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+SET_OPEN_DURATION,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    //添加设备
    public Boolean addDevice(Map<String,String> map) throws IOException {
        String resultData = sendPostDataByMap(BASE_URL+ADD_DEVICE,map,"UTF-8");
        JSONObject jsonObject = JSON.parseObject(resultData);
        Boolean result =jsonObject.getBoolean("success");
        if (result.equals(false)){
            System.out.println(jsonObject.get("message"));
        }
        return result;
    }

    /**
     * post请求传输map数据
     *
     * @param url
     * @param map
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static String sendPostDataByMap(String url, Map<String, String> map, String encoding) throws ClientProtocolException, IOException {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();
        return result;
    }

    /**
     * get请求传输数据
     *
     * @param url
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static String sendGetData(String url, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", "application/json");
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        }
        // 释放链接
        response.close();

        return result;
    }
}

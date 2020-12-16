package com.mx.probim.projectdocument.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class OSSClientConstants {
    //阿里云API的外网域名
    public static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    public static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    public static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    public static String BUCKET_NAME;
    //阿里云API的文件夹名称
    public static String FOLDER;

    @Value("${aliyun.endPoint}")
    public void setENDPOINT(String ENDPOINT) {
        OSSClientConstants.ENDPOINT = ENDPOINT;
    }

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSClientConstants.ACCESS_KEY_ID = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSClientConstants.ACCESS_KEY_SECRET = accessKeySecret;
    }

    @Value("${aliyun.bucketName}")
    public void setBucketName(String bucketName) {
        BUCKET_NAME = bucketName;
    }

    @Value("${aliyun.Folder}")
    public void setFOLDER(String FOLDER) {
        OSSClientConstants.FOLDER = FOLDER;
    }
}

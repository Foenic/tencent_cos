package com.usercenter.cos.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

/**
 * 腾讯云COS文件上传工具类
 * FILENAME:  COSClientUtil
 *
 * @author foenic
 * @email foenicnl@163.com
 * @date 6/17/0017  17
 * DESCRIPTION COS工具类
 */
@Slf4j
public class TencentCOSUploadFileUtil {
    // 存储桶名称
    private static final String BUCKET_NAME = "xingqiu-tuchuang-1256524210";
    //secretId 秘钥id
    private static final String SECRET_ID = "AKIDb0B7nDOJzPaVBbmhNnzwd5HDuLXT9alg";
    //SecretKey 秘钥
    private static final String SECRET_KEY = "h9LHyPaWCcmhMlvpUt3yykGXYJo44oJY";
    // 腾讯云 自定义文件夹名称
    private static final String PREFIX = "/12138/";
    // 访问域名
    public static final String URL = "https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com";
    // 创建COS 凭证
    private static final COSCredentials CREDENTIALS = new BasicCOSCredentials(SECRET_ID,SECRET_KEY);
    // 配置 COS 区域 就购买时选择的区域
    private static final ClientConfig CLIENT_CONFIG = new ClientConfig(new Region("ap-shanghai"));

    public static String uploadfile(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(CREDENTIALS, CLIENT_CONFIG);
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : null;
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName =PREFIX+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        return URL+fileName;
    }
}

package com.usercenter.cos.controller;

import com.usercenter.cos.utils.TencentCOSUploadFileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * FILENAME:  C
 *
 * @author foenic
 * @email foenicnl@163.com
 * @date 6/17/0017  17
 * DESCRIPTION
 */
@RestController
public class UploadFileController {
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        if (null == file) {
            return "文件为空";
        }
        String filePath = TencentCOSUploadFileUtil.uploadfile(file);
        return "上传成功，访问地址为:"+filePath;
    }
}

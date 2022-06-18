package com.usercenter.cos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FILENAME:  COSConfig
 *
 * @author foenic
 * @email foenicnl@163.com
 * @date 6/17/0017  17
 * DESCRIPTION 对象存储配置
 */

@Data
@Component
@ConfigurationProperties(prefix = "cos")
public class COSConfig {
    private String baseUrl;
    private String accessKey;
    private String secretKey;
    private String regionName;
    private String bucketName;
    private String folderPrefix;
}

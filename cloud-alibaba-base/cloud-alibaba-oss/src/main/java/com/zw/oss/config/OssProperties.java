package com.zw.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessSecret;
    /**
     * 存储空间名称
     */
    private String bucketName;
    /**
     * 存储空间子目录
     */
    private String fileHost;
}

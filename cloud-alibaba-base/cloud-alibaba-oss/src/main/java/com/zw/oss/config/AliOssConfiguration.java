package com.zw.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.http.impl.pool.BasicConnFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Configuration
public class AliOssConfiguration {
    @Resource
    OssProperties ossProperties;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)
    public OSS ossClient() {
        OSS oss = new OSSClientBuilder()
                .build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessSecret());
        if (!oss.doesBucketExist(ossProperties.getBucketName())) {
            oss.createBucket(ossProperties.getBucketName());
        }
        return oss;
    }
}

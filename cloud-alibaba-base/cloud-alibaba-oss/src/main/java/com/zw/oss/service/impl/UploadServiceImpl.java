package com.zw.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import com.zw.oss.config.OssProperties;
import com.zw.oss.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Resource
    OSS oss;
    @Resource
    OssProperties properties;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String key = properties.getFileHost() + File.separator + new Random().nextInt(10000) + System.currentTimeMillis() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        log.info(oss.toString());
        oss.putObject(properties.getBucketName(),
                key, multipartFile.getInputStream());
        oss.shutdown();
        return key;
    }


    @Override
    public List<String> list() {
        return null;
    }

    @Override
    public void download() {

    }

    @Override
    public int delete() {
        return 0;
    }

    public static void main(String[] args) {
        String image = "12345.111.png";
        String suffix = image.substring(image.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + suffix;
        System.out.println(fileName);
    }
}

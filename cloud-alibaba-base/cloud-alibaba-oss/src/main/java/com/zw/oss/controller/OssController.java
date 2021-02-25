package com.zw.oss.controller;

import com.zw.oss.result.ResponseResult;
import com.zw.oss.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhangwei
 */
@RestController
@RequestMapping("/oss/ali/")
public class OssController {
    @Resource
    UploadService uploadService;

    /**
     * 上传接口
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String upload = uploadService.upload(file);
        return ResponseResult.success(upload);
    }
}

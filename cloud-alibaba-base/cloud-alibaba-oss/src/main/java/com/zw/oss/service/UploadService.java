package com.zw.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadService {
    /**
     * 上传文件
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 查看文件
     *
     * @return
     */
    List<String> list();

    /**
     * 下载文件
     */
    void download();

    /**
     * 删除文件
     *
     * @return
     */
    int delete();
}

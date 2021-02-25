package com.zw.oss.constants;

import lombok.Getter;

/**
 * @author zhangwei
 */

@Getter
public enum FileType {
    /**
     * 视频
     */
    VIDEO(1, "video"),
    /**
     * 图片类型
     */
    IMAGE(2, "image");
    /**
     * 文件类型
     */
    String type;
    /**
     * 文件的id
     */
    int id;

    FileType(int id, String type) {
        this.id = id;
        this.type = type;
    }

}

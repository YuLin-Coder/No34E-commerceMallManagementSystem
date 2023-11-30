package pers.zjh.shop.util;

import org.springframework.web.multipart.MultipartFile;

// 图片上传辅助类
public class UploadedImageFile {
    MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}

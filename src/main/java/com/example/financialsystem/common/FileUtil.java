package com.example.financialsystem.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/18 14:34
 */
public class FileUtil {
//    public static final String UPLOAD_PATH = "D:\\coding\\javaProject\\financialSystem\\src\\main\\resources\\static\\upload";
    public static final String UPLOAD_PATH = "/www/wwwroot/financialWeb/upload";



    public static String uploads(MultipartFile file) throws IOException {

        //拿到文件后缀
        final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);

        //文件名
        String fileName = UUID.randomUUID().toString().replace("-","") + "." + fileSuffix;

        File descFile = new File(UPLOAD_PATH, fileName);
        file.transferTo(descFile);

        // 文件URL
        String myUrl = "/upload/" + fileName;
        return myUrl;
    }
}
    
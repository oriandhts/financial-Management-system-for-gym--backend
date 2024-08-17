package com.example.financialsystem.controller;

import com.example.financialsystem.common.FileUtil;
import com.example.financialsystem.common.Result;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/18 14:22
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/file")
public class FileController {

    @PostMapping("/uploads")
    public Result uploads(MultipartFile file) throws IOException {
        String url = FileUtil.uploads(file);
        return Result.success(url);
    }

}

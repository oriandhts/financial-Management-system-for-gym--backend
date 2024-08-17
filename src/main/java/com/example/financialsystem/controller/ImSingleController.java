package com.example.financialsystem.controller;

import com.example.financialsystem.common.Result;
import com.example.financialsystem.entity.ImSingle;
import com.example.financialsystem.service.ImSingleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2024/1/12 13:20
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/imsingle")
public class ImSingleController {

    @Resource
    private ImSingleService imSingleService;

    @GetMapping
    public Result findByFromUsername(@RequestParam String fromUser, @RequestParam String toUser)
    {
        List<ImSingle> allChat = imSingleService.findByUsername(fromUser,toUser);
        return Result.success(allChat);
    }

    @PostMapping("/insertChat")
    public Result insertContent(@RequestBody ImSingle imSingle)
    {
        imSingleService.insertContent(imSingle);
        return Result.success();
    }
}
    
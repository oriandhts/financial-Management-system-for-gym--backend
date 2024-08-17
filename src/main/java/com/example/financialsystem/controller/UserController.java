package com.example.financialsystem.controller;

import cn.hutool.core.util.StrUtil;
import com.example.financialsystem.common.Result;
import com.example.financialsystem.entity.DutyRecord;
import com.example.financialsystem.entity.User;
import com.example.financialsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/14 21:40
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class UserController {

    @Autowired
    //去拿到UserService
    UserService userService;


    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.insertUser(user);
        return Result.success();

    }

    @PostMapping("/enter")
    public Result enter(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("数据输入不合法");
        }
        user = userService.selectUser(user);
        return Result.success(user);
    }

    @GetMapping("/selectUser")
    public Result selectUser(){
       List<User> userAll = userService.selectAllUser();
       return Result.success(userAll);
    }

    //只获取名字
    @GetMapping("/selectAllName")
    public Result selectAllName(){
        List<String> nameAll = userService.selectAllName();
        return Result.success(nameAll);
    }

    @PostMapping("/editUser")
    public Result editUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return Result.success();
    }

    @PostMapping("/suspendUser")
    public Result suspendUser(@RequestBody User user) {
        userService.suspendUser(user);
        return Result.success();
    }

    @PostMapping("/insertDuty")
    public Result insertDuty(@RequestBody DutyRecord dutyRecord) {
        userService.insertDuty(dutyRecord);
        return Result.success();
    }

    @GetMapping("/selectDuty")
    public Result selectDuty() {
        List<DutyRecord> myRecord = userService.selectDuty();
        return Result.success(myRecord);
    }

    @GetMapping("/deleteDuty/{dutyId}")
    public Result deletePri(@PathVariable Integer dutyId) {
        userService.deleteDuty(dutyId);
        return Result.success();
    }

}
    
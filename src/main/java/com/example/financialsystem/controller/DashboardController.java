package com.example.financialsystem.controller;

import com.example.financialsystem.common.Result;
import com.example.financialsystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/24 23:35
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/getRecentData")
    public Result getData(@RequestParam String startDate, @RequestParam String endDate) {
        Map<String,Object> myData = dashboardService.getAllData(startDate,endDate);
        return Result.success(myData);
    }

    @GetMapping("/salaryPart")
    public Result getSalary(@RequestParam String startDate, @RequestParam String endDate) {
        Map<String,Object> mySalary = dashboardService.getSalary(startDate,endDate);
        return Result.success(mySalary);
    }

}
    
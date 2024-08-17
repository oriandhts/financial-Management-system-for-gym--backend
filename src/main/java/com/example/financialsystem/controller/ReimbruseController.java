package com.example.financialsystem.controller;

import com.example.financialsystem.common.Result;
import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.Reimburse;
import com.example.financialsystem.service.ReimbruseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/18 15:28
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reimburse")
public class ReimbruseController {

    @Autowired
    ReimbruseService reimbruseService;


    @PostMapping("/insert")
    public Result reimInsert(@RequestBody  Reimburse reimburse) {
        reimbruseService.reimInsert(reimburse);
        return Result.success();
    }

    @GetMapping("/selectReim")
    public Result selectAll() {
        List<Reimburse> reimburseList = reimbruseService.selectAllReim();
        return Result.success(reimburseList);
    }

    @GetMapping("/selectByTab/{verifyType}")
    public Result selectById(@PathVariable String verifyType) {
        List<Reimburse> reimBytab = reimbruseService.selectByTab(verifyType);
        return Result.success(reimBytab);
    }

    @PostMapping("/finishReim")
    public Result changeStatus(@RequestBody Reimburse reimburse){
        reimbruseService.changeStatus(reimburse);
        return Result.success();
    }

    @PostMapping("/questionReim")
    public Result questionStatus(@RequestBody Reimburse reimburse){
        reimbruseService.questionStatus(reimburse);
        return Result.success();
    }

    @GetMapping("/selectTopTen")
    public Result selectTopTen() {
        Map<String,Object> allData = reimbruseService.selectTopten();
        return Result.success(allData);
    }

    @GetMapping("/deleteReim")
    public Result deleteReim(@RequestParam Integer reimId) {
        reimbruseService.deleteReim(reimId);
        return Result.success();
    }

    @PostMapping("/editReim")
    public Result editReim(@RequestBody Reimburse reimburse) {
        reimbruseService.editReim(reimburse);
        return Result.success();
    }
}
    
package com.example.financialsystem.controller;

import com.example.financialsystem.common.Result;
import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.privateTutor;
import com.example.financialsystem.service.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/17 16:05
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/memberCard")
public class MemberCardController {
    @Resource
    //去拿到UserService
    MemberCardService memberCardService;
    @PostMapping("/addMember")
    public Result add(@RequestBody MemberCard memberCard) {
        memberCardService.insertMember(memberCard);
        return Result.success();

    }

    @GetMapping("/selectMember")
    public Result selectbyDay() {
        List<MemberCard> memberCardList = memberCardService.selectByDay();
        return Result.success(memberCardList);
    }

    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam Integer count, @RequestParam Integer offset) {
        Map<String,Object> memberCardList = memberCardService.selectAllMember(count,offset);
        return Result.success(memberCardList);
    }

    @GetMapping("/selectRealAll")
    public Result selectRealAll() {
        List<MemberCard> memberCardList = memberCardService.selectAllRealMember();
        return Result.success(memberCardList);
    }

    @PostMapping("/editMember")
    public Result editMember(@RequestBody MemberCard memberCard) {
        memberCardService.editMember(memberCard);
        return Result.success();
    }

    @GetMapping("/deleteMember/{memberId}")
    public Result deleteMember(@PathVariable Integer memberId) {
        memberCardService.deleteMember(memberId);
        return Result.success();
    }

    @GetMapping("/calculateAll")
    public Result calculateMember(){
        Map<String,Integer> calculateNum = memberCardService.calculateAll();
        return Result.success(calculateNum);
    }


    //私教部分

    @GetMapping("/selectAllPri")
    public Result selectAllPri(){
        List<privateTutor> privateTutors = memberCardService.selectAllPri();
        return Result.success(privateTutors);
    }

    @GetMapping("/selectAllPriDate")
    public Result selectAllPriDate(){
        List<privateTutor> privateTutors = memberCardService.selectAllPriDate();
        return Result.success(privateTutors);
    }

    @PostMapping("/insertPri")
    public Result insertAllPri(@RequestBody privateTutor privatetutor) {
        memberCardService.insertPri(privatetutor);
        return Result.success();
    }

    @PostMapping("/editPri")
    public Result editPri(@RequestBody privateTutor privateTutor){
        memberCardService.editPri(privateTutor);
        return Result.success();
    }

    @GetMapping("/deletePri/{privateId}")
    public Result deletePri(@PathVariable Integer privateId) {
        memberCardService.deletePri(privateId);
        return Result.success();
    }
}
    
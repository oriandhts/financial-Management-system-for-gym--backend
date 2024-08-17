package com.example.financialsystem.service;

import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.Reimburse;
import com.example.financialsystem.mapper.ReimbruseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/18 15:29
 */

@Service
public class ReimbruseService {

    @Autowired
    ReimbruseMapper reimbruseMapper;


    public void reimInsert(Reimburse reimburse) {
        //添加填写时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter); // "1986-04-08 12:30:00"
        reimburse.setBuyDate(formattedDateTime);
        reimbruseMapper.insert(reimburse);
    }

    public List<Reimburse> selectAllReim() {

        return reimbruseMapper.selectAll();
    }

    public List<Reimburse> selectByTab(String verifyType) {

        return reimbruseMapper.selectByTab(verifyType);
    }

    public void changeStatus(Reimburse reimburse) {
        reimburse.setVerifyType("success");
        reimbruseMapper.updateVerify(reimburse);
    }

    public void questionStatus(Reimburse reimburse) {
        reimburse.setVerifyType("question");
        reimbruseMapper.updateVerify(reimburse);
    }

    public Map<String, Object> selectTopten() {
        Map<String,Object> allData = new HashMap<>();
        List<Reimburse> topTen = reimbruseMapper.topTen();
        Integer allAmount = reimbruseMapper.allRecord();
        Integer verifyAmount = reimbruseMapper.verifyRecord();
        Integer successAmount = reimbruseMapper.successRecord();

        allData.put("topTen",topTen);
        allData.put("allAmount",allAmount);
        allData.put("verifyAmount",verifyAmount);
        allData.put("successAmount",successAmount);
        return allData;
    }

    public void deleteReim(Integer reimId) {
        reimbruseMapper.deleteReim(reimId);
    }

    public void editReim(Reimburse reimburse) {
        reimbruseMapper.editReim(reimburse);
    }
}

    
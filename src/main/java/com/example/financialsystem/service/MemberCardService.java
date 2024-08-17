package com.example.financialsystem.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.privateTutor;
import com.example.financialsystem.mapper.MemberCardMapper;
import com.example.financialsystem.mapper.privateTutorMapper;
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
 * 日期: 2023/12/17 16:10
 */
@Service
public class MemberCardService {
    @Autowired
    MemberCardMapper memberCardMapper;

    @Autowired
    privateTutorMapper privateTutorMapper;

    public void insertMember(MemberCard memberCard) {
        //计算finalPrice
        Integer finalPrice = memberCard.getPrice() - memberCard.getGroupPurchase();
        memberCard.setFinalPrice(finalPrice);
        //添加填写时间
        LocalDateTime now = LocalDateTime.now();
        memberCard.setDate(now);
       // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter); // "1986-04-08 12:30:00"
        memberCard.setQueryDate(formattedDateTime);
        memberCardMapper.insert(memberCard);
    }

    public Map<String, Object> selectAllMember(Integer count, Integer offset) {
        Integer offsetNum = (offset - 1) * count;
        List<MemberCard> dbMemberCard = memberCardMapper.selectAllByPage(offsetNum,count);
        //计算有多少条数据
        Integer cardLength = memberCardMapper.selectAll();
        // 创建一个Map，其中键是String，值是Object
        Map<String, Object> memberCardData = new HashMap<>();
        memberCardData.put("totalNum",cardLength);
        memberCardData.put("data",dbMemberCard);
        return memberCardData;
    }

    public List<MemberCard> selectByDay() {
        return memberCardMapper.selectToday();
    }

    public void editMember(MemberCard memberCard) {
        //计算finalPrice
        Integer finalPrice = memberCard.getPrice() - memberCard.getGroupPurchase();
        memberCard.setFinalPrice(finalPrice);
        memberCardMapper.update(memberCard);
    }

    public void deleteMember(Integer memberId) {
        memberCardMapper.deleteById(memberId);
    }

    public Map<String,Integer> calculateAll() {
        Map<String,Integer> memberCardDate = new HashMap<>();
        Integer term = memberCardMapper.selectTermCard();
        Integer month = memberCardMapper.selectMonthCard();
        Integer day = memberCardMapper.selectDayCard();
        Integer season = memberCardMapper.selectSeasonCard();
        Integer sumUp = term + month + day + season;

        Integer cardMoney = memberCardMapper.selectCardMoney();
        Integer tutorMoney = memberCardMapper.selectTutorMoney();
        if(ObjectUtil.isNull(tutorMoney)) {
            tutorMoney = 0;
        }

        if(ObjectUtil.isNull(cardMoney)) {
            cardMoney = 0;
        }
        Integer sumMoney = cardMoney + tutorMoney;
        memberCardDate.put("term",term);
        memberCardDate.put("month",month);
        memberCardDate.put("day",day);
        memberCardDate.put("season",season);
        memberCardDate.put("sumUp",sumUp);
        memberCardDate.put("cardMoney",cardMoney);
        memberCardDate.put("tutorMoney",tutorMoney);
        memberCardDate.put("sumMoney",sumMoney);

        return memberCardDate;


    }

    // 私教表部分
    public List<privateTutor> selectAllPri() {
     return   privateTutorMapper.selectAll();
    }

    public void insertPri(privateTutor privatetutor) {
        //计算finalPrice
        Integer finalPrice = privatetutor.getPriPrice() - privatetutor.getDiscount();
        privatetutor.setFinalPriPrice(finalPrice);
        //添加填写时间
        LocalDateTime now = LocalDateTime.now();
        privatetutor.setTutorDate(now);
        //格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter); // "1986-04-08 12:30:00"
        privatetutor.setQueryPriDate(formattedDateTime);
        privateTutorMapper.insertPri(privatetutor);


    }

    public void deletePri(Integer privateId) {
        privateTutorMapper.deleteById(privateId);
    }

    public void editPri(privateTutor privatetutor) {
        privateTutorMapper.update(privatetutor);
    }

    public List<privateTutor> selectAllPriDate() {
        return   privateTutorMapper.selectAllDate();    }

    public List<MemberCard> selectAllRealMember() {
        return memberCardMapper.selectRealAll();
    }
}
    
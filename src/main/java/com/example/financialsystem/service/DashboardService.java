package com.example.financialsystem.service;

import com.example.financialsystem.entity.DutyCount;
import com.example.financialsystem.entity.salaryTable;
import com.example.financialsystem.mapper.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/24 23:36
 */

@Service
public class DashboardService {

    @Autowired
    DashboardMapper dashboardMapper;
    public Map<String,Object> getAllData(String startDate, String endDate) {
        Map<String,Object> dashboardMap = new HashMap<>();
        Integer applyCard = dashboardMapper.getApplyCard(startDate,endDate);
        Integer privateCard = dashboardMapper.getPrivateCard(startDate,endDate);

        if(applyCard == null) {
            applyCard = 0;
        }

        if(privateCard == null) {
            privateCard = 0;
        }

        Integer revenue = applyCard + privateCard;
        dashboardMap.put("applyCard",applyCard);
        dashboardMap.put("privateCard",privateCard);
        dashboardMap.put("revenue",revenue);

        Integer termCard = dashboardMapper.termCard(startDate,endDate);
        Integer monthCard = dashboardMapper.monthCard(startDate,endDate);
        Integer seasonCard = dashboardMapper.seasonCard(startDate,endDate);
        Integer perCard = dashboardMapper.perCard(startDate,endDate);
        Integer allCard = termCard + monthCard + seasonCard + perCard;
        dashboardMap.put("termCard",termCard);
        dashboardMap.put("monthCard",monthCard);
        dashboardMap.put("seasonCard",seasonCard);
        dashboardMap.put("perCard",perCard);
        dashboardMap.put("allCard",allCard);

        List<Integer> combineChartApply = dashboardMapper.combineApply(startDate,endDate);
        List<String> combineApplyDate = dashboardMapper.combineApplyDate(startDate,endDate);
        List<Integer> combineChartPri = dashboardMapper.combinePrivate(startDate,endDate);
        List<String> combinePriDate = dashboardMapper.combinePriDate(startDate,endDate);

        if (combineApplyDate == null) {
           combineChartApply =  new ArrayList<>();
           combineApplyDate = new ArrayList<>();
        }

        if(combinePriDate == null) {
            combinePriDate = new ArrayList<>();
            combineChartPri = new ArrayList<>();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = generateDateRange(startDate, endDate, sdf);

        List<Integer> newList = new ArrayList<>();
        List<Integer> newPriList = new ArrayList<>();
        List<Integer> allList = new ArrayList<>();
        for (String element : dateList) {

            if(combineApplyDate.contains(element)){
                newList.add(combineChartApply.get(0));
                combineChartApply.remove(0);
            }
            else {
                newList.add(0);
            }

            if(combinePriDate.contains(element)){
                newPriList.add(combineChartPri.get(0));
                combineChartPri.remove(0);
            }
            else {
                newPriList.add(0);
            }
        }

        for(int i = 0 ;  i < newList.size() ; i++) {
            allList.add(newList.get(i) + newPriList.get(i));
        }

        //热力图数据
        List<Object> hotList = new ArrayList<>();
        for (int j = 0 ; j < dateList.size(); j++) {
            List<Object> myData = new ArrayList<>();
            myData.add(dateList.get(j));
            myData.add(newList.get(j));
            hotList.add(myData);
        }

        dashboardMap.put("combineChartApply",newList);
        dashboardMap.put("combineChartPri",newPriList);
        dashboardMap.put("combineAll",allList);
        dashboardMap.put("datelist",dateList);
        dashboardMap.put("hotList",hotList);

        //雷达图数据
        List<Object> vadarData = new ArrayList<>();
        vadarData.add(applyCard);
        Double getReimburse = dashboardMapper.getReimburse(startDate, endDate);
        if(getReimburse == null) {
            getReimburse = 0.0;
        }
        vadarData.add(getReimburse);
        vadarData.add(privateCard);
        //办卡提成
        List<salaryTable> staffSalary = dashboardMapper.staffSalary(startDate,endDate);
        List<salaryTable> newstaffSalary = getStaffSalary(staffSalary);
        //获取提成总和
        Double commissionAll = 0.0 ;
        for(salaryTable element : newstaffSalary){
            commissionAll += element.getSalary();
        }
        vadarData.add(commissionAll);
        //值班工资
        vadarData.add(1350);
        //办卡人数
        vadarData.add(allCard);
        dashboardMap.put("vadarData",vadarData);


        return dashboardMap;
    }

    public static List<String> generateDateRange(String startDateStr, String endDateStr, SimpleDateFormat sdf) {
        List<String> dateList = new ArrayList<>();
        try {
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            while (!calendar.getTime().after(endDate)) {
                dateList.add(sdf.format(calendar.getTime()));
                calendar.add(Calendar.DATE, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    public Map<String, Object> getSalary(String startDate, String endDate) {
        //获取总营收额,私教部分,办卡部分
        Integer applyCard = dashboardMapper.getApplyCard(startDate, endDate);
        Integer privateCard = dashboardMapper.getPrivateCard(startDate, endDate);
        if(applyCard == null){
            applyCard = 0;
        }
        if(privateCard == null){
            privateCard = 0;
        }
        Integer allRevenue = applyCard + privateCard;

        Map<String,Object> salaryMap = new HashMap<>();
        //管理人员工资
        Double  gymManager;
        Double membership;
        Double finManager;
        Double techManager;
        Double promoManager;
        Double trainerManager;

        if(allRevenue >= 200000) {
            gymManager = allRevenue * 0.015 + 1500.0;
            membership = applyCard * 0.015 + 1400.0;
            finManager = 1200.0;
            techManager = 800.0;
            promoManager = 800.0;
            trainerManager = 1400.0;
        }
        else {
            gymManager = allRevenue * 0.010 + 1000;
            membership = applyCard * 0.010 + 900;
            finManager = 700.0;
            techManager = 400.0;
            promoManager = 400.0;
            trainerManager = 900.0;
        }

        salaryMap.put("gymManager",gymManager);
        salaryMap.put("membership",membership);
        salaryMap.put("finManager",finManager);
        salaryMap.put("techManager",techManager);
        salaryMap.put("promoManager",promoManager);
        salaryMap.put("trainerManager",trainerManager);

        //办卡人员工资
        List<salaryTable> staffSalary = dashboardMapper.staffSalary(startDate,endDate);
        List<salaryTable> newstaffSalary = getStaffSalary(staffSalary);
        salaryMap.put("staffSalary",newstaffSalary);

        Double getReimburse = dashboardMapper.getReimburse(startDate, endDate);
        if(getReimburse == null) {
            getReimburse = 0.0;
        }
        Double getAllsalary = gymManager + membership + finManager + techManager + promoManager + trainerManager;
        //获取提成总和
        Double commissionAll = 0.0 ;
        for(salaryTable element : newstaffSalary){
            commissionAll += element.getSalary();
        }

        //获取私教工资
        Double privateSalary = 0.0;

        //获取值班工资
        Double dailyDuty = dashboardMapper.dutyAll(startDate,endDate);
        if(dailyDuty == null) {
            dailyDuty = 0.0;
        }

        dailyDuty = dailyDuty * 10;

        List<DutyCount> myCount = dashboardMapper.dutyAllPer(startDate,endDate);
        //获取其他
        Double other = 0.0;

        Double allExpense = getReimburse + getAllsalary + commissionAll + other + dailyDuty;

        salaryMap.put("allReimburse",getReimburse);
        salaryMap.put("managerExpense",getAllsalary);
        salaryMap.put("commissionAll",commissionAll);
        salaryMap.put("privateSalary",privateSalary);
        salaryMap.put("dailyDuty",dailyDuty);
        salaryMap.put("other",other);
        salaryMap.put("allExpense",allExpense);
        salaryMap.put("dutyAll",myCount);


        return salaryMap;
    }

    public static List<salaryTable> getStaffSalary(List<salaryTable> staffSalary) {
        List<salaryTable> newstaffSalay = new ArrayList<>();;
        for( salaryTable element : staffSalary){
            if(element.getPerformance() >= 0 && element.getPerformance() <= 5000){
                element.setRatio("0.5%");
                element.setSalary(element.getPerformance() * 0.005);
            }else if(element.getPerformance() > 5000 && element.getPerformance() <= 10000){
                element.setRatio("1.0%");
                element.setSalary(element.getPerformance() * 0.01);
            }else if(element.getPerformance() > 10000 && element.getPerformance() <= 15000){
                element.setRatio("1.5%");
                element.setSalary(element.getPerformance() * 0.015);
            }else if(element.getPerformance() > 15000 && element.getPerformance() <= 20000){
                element.setRatio("2.0%");
                element.setSalary(element.getPerformance() * 0.020);
            }else if(element.getPerformance() > 20000 && element.getPerformance() <= 30000){
                element.setRatio("2.5%");
                element.setSalary(element.getPerformance() * 0.025);
            }else if(element.getPerformance() > 30000 && element.getPerformance() <= 40000){
                element.setRatio("3.0%");
                element.setSalary(element.getPerformance() * 0.030);
            }else if(element.getPerformance() > 40000 && element.getPerformance() <= 50000){
                element.setRatio("3.5%");
                element.setSalary(element.getPerformance() * 0.035);
            }else if(element.getPerformance() > 50000 && element.getPerformance() <= 60000){
                element.setRatio("4.0%");
                element.setSalary(element.getPerformance() * 0.040);
            }else if(element.getPerformance() > 60000 && element.getPerformance() <= 70000){
                element.setRatio("4.5%");
                element.setSalary(element.getPerformance() * 0.045);
            }else {
                element.setRatio("5.0%");
                element.setSalary(element.getPerformance() * 0.050);
            }

            newstaffSalay.add(element);
        }

        return newstaffSalay;
    }

}
    
package com.example.financialsystem.mapper;

import com.example.financialsystem.entity.DutyCount;
import com.example.financialsystem.entity.salaryTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface DashboardMapper {


    @Select("select sum(finalPrice) from `membercard` where Date(date) between  #{startDate} AND #{endDate}")
    Integer getApplyCard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select sum(finalPriPrice) from `privatetutor` Where Date(queryPriDate) between #{startDate} AND #{endDate}")
    Integer getPrivateCard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select count(*) from `membercard` Where Date(queryDate) between #{startDate} AND #{endDate} AND cardType = '学期卡'")
    Integer termCard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select count(*) from `membercard` Where Date(queryDate) between #{startDate} AND #{endDate} AND cardType = '季卡'")
    Integer seasonCard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select count(*) from `membercard` Where Date(queryDate) between #{startDate} AND #{endDate} AND cardType = '月卡'")
    Integer monthCard(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Select("select sum(finalPrice) from `membercard` Where Date(queryDate) >= #{startDate} AND Date(queryDate) <= #{endDate} GROUP BY Date(queryDate) ORDER BY Date(queryDate)")
    List<Integer> combineApply(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select sum(finalPriPrice) from `privatetutor` Where Date(queryPriDate) >= #{startDate} AND Date(queryPriDate) <= #{endDate} GROUP BY Date(queryPriDate) ORDER BY Date(queryPriDate)")
    List<Integer> combinePrivate(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Select("select DISTINCT queryDate from `membercard` Where Date(queryDate) >= #{startDate} AND Date(queryDate) <= #{endDate} ORDER BY Date(queryDate)")
    List<String> combineApplyDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select DISTINCT queryPriDate from `privatetutor` Where Date(queryPriDate) >= #{startDate} AND Date(queryPriDate) <= #{endDate} ORDER BY Date(queryPriDate)")
    List<String> combinePriDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select staff,SUM(finalPrice) AS performance from `membercard` Where Date(queryDate) >= #{startDate} AND Date(queryDate) <= #{endDate} GROUP BY staff")
    List<salaryTable> staffSalary(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select SUM(buyAmount) from `reimburse` Where Date(buyDate) >= #{startDate} AND Date(buyDate) <= #{endDate}")
    Double getReimburse(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select count(*) from `membercard` Where Date(queryDate) between #{startDate} AND #{endDate} AND cardType = '次卡'")
    Integer perCard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //获取每个人的工时
    @Select("select dutyName,SUM(dutyCount) AS dutyAll from `ondutyrecord` Where Date(fillTime) >= #{startDate} AND Date(fillTime) <= #{endDate} GROUP BY dutyName ")
    List<DutyCount> dutyAllPer(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select SUM(dutyCount) AS dutyAll from `ondutyrecord` Where Date(fillTime) >= #{startDate} AND Date(fillTime) <= #{endDate}")
    Double dutyAll(@Param("startDate") String startDate, @Param("endDate") String endDate);
}

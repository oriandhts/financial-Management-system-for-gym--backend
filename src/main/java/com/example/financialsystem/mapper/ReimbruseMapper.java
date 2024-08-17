package com.example.financialsystem.mapper;

import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.Reimburse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReimbruseMapper {
    @Insert("insert into `reimburse` (buyer,buyDate,buyType,buyAmount,proof,fillDate,reimRemarks,verifyType) " +
            "values(#{buyer},#{buyDate},#{buyType},#{buyAmount},#{proof},#{fillDate},#{reimRemarks},#{verifyType})")
    void insert(Reimburse reimburse);

    @Update(" update  `reimburse` set buyer = #{buyer} ,buyType = #{buyType} ,buyAmount=#{buyAmount},proof = #{proof}," +
            " reimRemarks = #{reimRemarks}, verifyType = #{verifyType} where reimId = #{reimId} ")
    void editReim(Reimburse reimburse);
    @Select("select * from `reimburse`")
    List<Reimburse> selectAll();
    @Select("select * from `reimburse` where verifyType = #{verifyType}")
    List<Reimburse> selectByTab(String verifyType);

    @Update("update `reimburse` set verifyType = #{verifyType} where reimId = #{reimId}")
    void updateVerify(Reimburse reimburse);


    @Select("select `buyAmount`, `buyType` from `reimburse` ORDER BY buyAmount DESC LIMIT 10"   )
    List<Reimburse> topTen();

    @Select("select sum(buyAmount) from `reimburse` ")
    Integer allRecord();

    @Select("select sum(buyAmount) from `reimburse` where verifyType = 'verifying' ")
    Integer verifyRecord();

    @Select("select sum(buyAmount) from `reimburse` where verifyType = 'success' ")
    Integer successRecord();


    @Delete("delete from `reimburse` where reimId = #{reimId}")
    void deleteReim(Integer reimId);



}


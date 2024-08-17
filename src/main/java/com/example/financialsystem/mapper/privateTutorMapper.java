package com.example.financialsystem.mapper;


import com.example.financialsystem.entity.MemberCard;
import com.example.financialsystem.entity.privateTutor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface privateTutorMapper {
    @Select("select * from `privatetutor`")
    List<privateTutor> selectAll();


    @Insert("insert into `privatetutor` (privateName,privatePhone,classNum,discount,transactor,tutorDate,tutorRemarks,finalPriPrice,priPrice,queryPriDate)" +
            "values(#{privateName},#{privatePhone},#{classNum},#{discount},#{transactor},#{tutorDate},#{tutorRemarks},#{finalPriPrice},#{priPrice},#{queryPriDate})")
    void insertPri(privateTutor privatetutor);


    //删除
    @Delete(" delete from `privatetutor` where privateId = #{privateId}")
    void deleteById(Integer privateId);

    //修改
    @Update(" update  `privatetutor` set privateName = #{privateName} ,privatePhone = #{privatePhone} ,classNum = #{classNum},discount = #{discount}," +
            " transactor = #{transactor},tutorDate = #{tutorDate},tutorRemarks = #{tutorRemarks}, finalPriPrice = #{finalPriPrice},priPrice=#{priPrice} where privateId = #{privateId} ")
    void update(privateTutor privatetutor);

    @Select("select * from `privatetutor` where Date(tutorDate) = CURDATE()")
    List<privateTutor> selectAllDate();
}

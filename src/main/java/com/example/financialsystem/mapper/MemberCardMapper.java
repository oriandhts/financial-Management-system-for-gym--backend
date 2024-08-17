package com.example.financialsystem.mapper;

import com.example.financialsystem.entity.MemberCard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberCardMapper {
    @Insert("insert into `membercard` (Date,memberName,phoneNumber,cardType,price,groupPurchase,staff,remarks,finalPrice,queryDate) " +
            "values(#{Date},#{memberName},#{phoneNumber},#{cardType},#{price},#{groupPurchase},#{staff},#{remarks},#{finalPrice},#{queryDate})")
    void insert(MemberCard memberCard);

    @Select("select * from `membercard` LIMIT #{offsetNum}, #{count} ")
    List<MemberCard> selectAllByPage(@Param("offsetNum") Integer offsetNum, @Param("count")  Integer count);

    @Select("select count(*) from `membercard`")
    Integer selectAll();

    @Select("select *from `membercard`")
    List<MemberCard> selectRealAll();

    @Select("select * from `membercard` where Date(date) = CURDATE() ")
    List<MemberCard> selectToday();


    @Update(" update  `membercard` set memberName = #{memberName} ,cardType = #{cardType} ,price=#{price},finalPrice = #{finalPrice}," +
            " groupPurchase = #{groupPurchase},staff = #{staff},remarks = #{remarks}, phoneNumber = #{phoneNumber} where memberId = #{memberId} ")
    void update(MemberCard memberCard);

    @Delete(" delete from `membercard` where memberId = #{memberId}")
    void deleteById(Integer memberId);


    @Select(" select count(*) from `membercard` where cardType = '学期卡' And  Date(date) = CURDATE()")
    Integer selectTermCard();

    @Select(" select count(*) from `membercard` where cardType = '次卡'  And  Date(date) = CURDATE()")
    Integer selectDayCard();

    @Select(" select count(*) from `membercard` where cardType = '月卡'  And  Date(date) = CURDATE()")
    Integer selectMonthCard();

    @Select(" select count(*) from `membercard` where cardType = '季卡'  And  Date(date) = CURDATE()")
    Integer selectSeasonCard();

    @Select(" select sum(finalPrice) from `membercard` where Date(date) =CURDATE() ")
    Integer selectCardMoney();

    @Select(" select sum(finalPriPrice) from `privatetutor` where Date(tutorDate) =CURDATE() ")
    Integer selectTutorMoney();
}


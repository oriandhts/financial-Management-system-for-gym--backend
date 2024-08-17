package com.example.financialsystem.mapper;


import com.example.financialsystem.entity.ImSingle;
import com.example.financialsystem.entity.MemberCard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImSingleMapper {

    @Select("select * from `imsingle` where (fromUser = #{fromUser} AND toUser = #{toUser}) OR (fromUser = #{toUser} AND toUser = #{fromUser})")
    List<ImSingle> findByUsername(@Param("fromUser") String fromUser, @Param("toUser") String toUser);


    @Insert("insert into `imsingle` (content,fromUser,fromavatar,time,type,toUser,toavatar,readed,keepChat,groupId) " +
            "values(#{content},#{fromUser},#{fromavatar},#{time},#{type},#{toUser},#{toavatar},#{readed},#{keepChat}),#{groupId}")
    void insertContent(ImSingle imSingle);
}

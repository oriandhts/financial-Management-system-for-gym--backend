package com.example.financialsystem.mapper;


import com.example.financialsystem.entity.DutyRecord;
import com.example.financialsystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
//把interface usermapper注入到容器里面去
public interface UserMapper {
    @Insert("insert into `user` (id,name,username,password,email,phone,role) " +
            "values(#{id},#{name},#{username},#{password},#{email},#{phone},#{role})")
    void insert(User user);

    @Select("select * from `user` where username = #{username}")
    User selectByUsername(String username);

    @Select("select * from `user` where id = #{userId}")
    User selectById(Integer userId);

    @Select("select * from `user`")
    List<User> selectAll();

    @Update("update `user` set role = #{role}, password = #{password} , username = #{username},name= #{name} where id=#{id} ")
    void update(User user);

    @Delete("delete from `user` where id = #{id}")
    void delete(String id);

    @Update("update `user` set suspend = #{suspend} where id=#{id} ")
    void suspend(User user);


    @Select("select name from `user`")
    List<String> selectAllName();


    @Insert("insert into `ondutyrecord` (dutyName,dutyTime,dutyDesc,fillIn,dutyCount,fillTime) " +
            "values(#{dutyName},#{dutyTime},#{dutyDesc},#{fillIn},#{dutyCount},#{fillTime})")
    void insertDuty(DutyRecord dutyRecord);

    @Select("select * from `ondutyrecord` where Date(fillTime) = CURDATE()")
    List<DutyRecord> selectDuty();

    @Delete(" delete from `ondutyrecord` where dutyId = #{dutyId}")
    void deleteDuty(Integer dutyId);
}

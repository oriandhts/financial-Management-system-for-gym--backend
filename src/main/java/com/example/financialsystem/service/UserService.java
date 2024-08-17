package com.example.financialsystem.service;

import com.example.financialsystem.Utils.TokenUtils;
import com.example.financialsystem.common.Result;
import com.example.financialsystem.entity.DutyRecord;
import com.example.financialsystem.entity.User;
import com.example.financialsystem.exception.ServiceException;
import com.example.financialsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/14 22:24
 */

@Service
//把UserService注入到容器,让容器帮我去管理
public class UserService {

    @Autowired
    UserMapper userMapper;


    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public User selectUser(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }


        if(dbUser.getSuspend() == 1) {
            throw new ServiceException("你的账户已经被禁用！请联系相关技术人员！");
        }

        String token = TokenUtils.genToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(String id) {
        userMapper.delete(id);
    }

    public void suspendUser(User user) {
        userMapper.suspend(user);
    }

    public List<String> selectAllName() {
        return userMapper.selectAllName();

    }

    public void insertDuty(DutyRecord dutyRecord) {

        //添加填写时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter); // "1986-04-08 12:30:00"
        dutyRecord.setFillTime(formattedDateTime);
        userMapper.insertDuty(dutyRecord);
    }

    public List<DutyRecord> selectDuty() {
        return userMapper.selectDuty();
    }

    public void deleteDuty(Integer dutyId) {
        userMapper.deleteDuty(dutyId);
    }
}
    
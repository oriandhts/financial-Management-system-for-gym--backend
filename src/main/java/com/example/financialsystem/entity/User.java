package com.example.financialsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/14 22:08
 */
@Getter
@Setter
//自动的创建get和set方法
public class User {
    private Integer suspend;
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private String token;
}
    
package com.example.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2024/1/12 13:27
 */

@Getter
@Setter
public class ImSingle {
    private Integer contentId;
    private String content;
    private String fromUser;
    private String fromavatar;
    private LocalDateTime time;
    private String type;
    private String toUser;
    private String toavatar;
    private Integer readed;
    private Integer keepChat;
    private Integer groupId;
}
    
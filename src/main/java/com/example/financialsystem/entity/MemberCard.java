package com.example.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/17 16:06
 */
@Getter
@Setter
public class MemberCard {
    private String memberId;
    private LocalDateTime Date;
    private String memberName;
    private String phoneNumber;
    private String cardType;
    private Integer price;
    private Integer groupPurchase;
    private Integer finalPrice;
    private String staff;
    private String remarks;
    private String queryDate;
}
    
package com.example.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/22 23:29
 */
@Getter
@Setter
public class privateTutor {
    private Integer privateId;
    private String privateName;
    private String privatePhone;
    private Integer classNum;
    private Integer priPrice;
    private Integer discount;
    private String transactor;
    private LocalDateTime tutorDate;
    private Integer finalPriPrice;
    private String tutorRemarks;
    private String queryPriDate;
}
    
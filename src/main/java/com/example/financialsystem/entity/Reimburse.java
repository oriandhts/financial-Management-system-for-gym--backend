package com.example.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/18 15:34
 */

@Getter
@Setter
public class Reimburse {
    private Integer reimId;
    private String buyer;
    private String buyDate;
    private Double buyAmount;
    private String buyType;
    private String reimRemarks;
    private String proof;
    private String fillDate;
    private String verifyType;

}
    
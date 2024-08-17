package com.example.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2024/2/28 16:14
 */

@Getter
@Setter
public class DutyRecord {
    private Integer dutyId;
    private String dutyName;
    private String dutyTime;
    private String dutyDesc;
    private String fillIn;
    private String fillTime;
    private Double dutyCount;

}
    
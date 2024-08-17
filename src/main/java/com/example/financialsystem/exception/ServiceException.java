package com.example.financialsystem.exception;

import lombok.Getter;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/15 13:29
 */
@Getter
public class ServiceException extends RuntimeException{

    private String code;
    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
    
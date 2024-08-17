package com.example.financialsystem.service;

import com.example.financialsystem.entity.ImSingle;
import com.example.financialsystem.mapper.ImSingleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2024/1/12 13:22
 */

@Service
public class ImSingleService {


    @Autowired
    private ImSingleMapper imSingleMapper;

    public List<ImSingle> findByUsername(String fromUser, String toUser) {
        List<ImSingle> list = imSingleMapper.findByUsername(fromUser,toUser);
        return list;
    }

    public void insertContent(ImSingle imSingle) {
        imSingleMapper.insertContent(imSingle);
    }
}
    
package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.hubu.dao.PageCountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    PageCountDAO pageCountDAO;
    public Integer getPageCount(String whereField,String tableName,String keyword){
        try {

            Integer count = pageCountDAO.selectPageCount(whereField,tableName,keyword);
            return count/10+1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}

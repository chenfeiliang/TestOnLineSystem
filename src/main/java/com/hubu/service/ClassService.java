package com.hubu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubu.dao.ClassDAO;
import com.hubu.dao.PageCountDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import com.hubu.pojo.User;
import com.hubu.utils.Myutils;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {
    @Autowired
    ClassDAO classDAO;
    private Integer pageCount = 10;

    public Integer addClass(MyClass myClass) {
        try {
            return classDAO.insertClass(myClass);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer updateClass(MyClass myClass) {
        try {
            return classDAO.updateClass(myClass);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Integer deleteClass(int classId) {
        try {
            return classDAO.deleteClass(classId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public PageInfo<MyClass> getPageClass(Integer currentPage) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<MyClass> myClasses = classDAO.selectClass();
            PageInfo<MyClass> pageClasses = new PageInfo<>(myClasses);
            int[] nums = pageClasses.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageClasses.setNavigatepageNums(result);
            return pageClasses;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<MyClass> getPageClassByKeyWord(Integer currentPage, String keyword) {
        try {
            PageHelper.startPage(currentPage, 10);
            List<MyClass> myClasses = classDAO.selectClassByKeyWord(keyword);
            PageInfo<MyClass> pageClasses = new PageInfo<>(myClasses);
            int[] nums = pageClasses.getNavigatepageNums();
            int[] result = Myutils.pageCount(currentPage, nums);
            pageClasses.setNavigatepageNums(result);
            return pageClasses;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public MyClass getClassByClassId(Integer classId) {
        try {
            return classDAO.selectClassById(classId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public Integer batchDeleteClassById(String classIds) {
        try {
            return classDAO.batchDeleteClassById(classIds);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<MyClass> getAllClass() {
        try {
            return classDAO.selectClass();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

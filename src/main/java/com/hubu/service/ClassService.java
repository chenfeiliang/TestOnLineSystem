package com.hubu.service;

import com.hubu.dao.ClassDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import com.hubu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<MyClass> getPageClass(Integer currentPage) {
        try {
            return classDAO.selectPageClass((currentPage-1)*pageCount,pageCount);
        }catch (Exception e){
            return null;
        }
    }

    public List<MyClass> getPageClassByKeyWord(Integer currentPage, String keyword) {
        try {
            return classDAO.selectPageClassByKeyWord((currentPage-1)*pageCount,pageCount,keyword);
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
}

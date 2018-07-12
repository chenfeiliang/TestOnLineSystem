package com.hubu.service;

import com.hubu.dao.ClassDAO;
import com.hubu.pojo.Msg;
import com.hubu.pojo.MyClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    ClassDAO classDAO;

    public Msg addClass(MyClass myClass) {
        return classDAO.insertClass(myClass) == 1 ? new Msg().success() : new Msg().fail();
    }

    public Msg updateClass(MyClass myClass) {
        return classDAO.updateClass(myClass) == 1 ? new Msg().success() : new Msg().fail();
    }

    public Msg deleteClass(int classId) {
        return classDAO.deleteClass(classId) == 1 ? new Msg().success() : new Msg().fail();
    }
}

package com.hubu.pojo;

import com.hubu.dto.UserDTO;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class User {
    @Size(message = "")
    private String account;
    private String realName;
    private String password;
    private int classId;
    private String mobile;
    private String image;
    private MyClass myClass;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", classId=" + classId +
                ", mobile='" + mobile + '\'' +
                ", image='" + image + '\'' +
                ", myClass=" + myClass +
                '}';
    }

    public User(UserDTO user) {
        account = user.getAccount();
        realName = user.getRealName();
        password = user.getPassword();
        classId = user.getClassId();
        mobile = user.getMobile();
        image = user.getImage();
    }
    public User(){

    }

}

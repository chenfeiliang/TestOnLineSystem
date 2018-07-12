package com.hubu.pojo;

public class MyClass {
    private int classId;
    private String className;

    @Override
    public String toString() {
        return "MyClass{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

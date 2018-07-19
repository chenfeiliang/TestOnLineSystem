package com.hubu.pojo;

import com.hubu.pojo.Lesson;
import com.hubu.pojo.Paper;
import com.hubu.pojo.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examin {
    private Integer examinId;
    private Integer paperId;
    private Integer lessonId;
    private String title;
    private String accounts;
    private String type;
    private Date beginTime;
    private Date endTime;
    private Paper paper;
    private Lesson lesson;
    private List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Examin{" +
                "examinId=" + examinId +
                ", paperId=" + paperId +
                ", lessonId=" + lessonId +
                ", title='" + title + '\'' +
                ", accounts='" + accounts + '\'' +
                ", type='" + type + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", paper=" + paper +
                ", lesson=" + lesson +
                ", users=" + users +
                '}';
    }

    public Integer getExaminId() {
        return examinId;
    }

    public void setExaminId(Integer examinId) {
        this.examinId = examinId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

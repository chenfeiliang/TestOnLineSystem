package com.hubu.pojo;


import java.util.List;

public class Paper {
    private Integer paperId;
    private String title;
    private Integer LessonId;
    private String questionIds;
    private String keys;
    private String creater;
    private Lesson lesson;
    private List<Question> questions;

    @Override
    public String toString() {
        return "Paper{" +
                "paperId=" + paperId +
                ", title='" + title + '\'' +
                ", LessonId=" + LessonId +
                ", questionIds='" + questionIds + '\'' +
                ", keys='" + keys + '\'' +
                ", creater='" + creater + '\'' +
                ", lesson=" + lesson +
                ", questions=" + questions +
                '}';
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLessonId() {
        return LessonId;
    }

    public void setLessonId(Integer lessonId) {
        LessonId = lessonId;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

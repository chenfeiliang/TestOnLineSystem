package com.hubu.pojo;

public class Question {
    private int questionId;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String questionKey;
    private int questionLevel;
    private int lessonId;
    private String creator;
    private Lesson lesson;

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", title='" + title + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", questionKey='" + questionKey + '\'' +
                ", questionLevel=" + questionLevel +
                ", lessonId=" + lessonId +
                ", creator='" + creator + '\'' +
                ", lesson=" + lesson +
                '}';
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(String questionKey) {
        this.questionKey = questionKey;
    }

    public int getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(int questionLevel) {
        this.questionLevel = questionLevel;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}

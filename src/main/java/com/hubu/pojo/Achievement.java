package com.hubu.pojo;

public class Achievement {
    private Integer achievementId;
    private String account;
    private Integer examinId;
    private Integer lessonId;
    private Integer classId;
    private Integer score;
    private Examin examin;
    private Lesson lesson;
    private MyClass myClass;

    @Override
    public String toString() {
        return "Achievement{" +
                "achievementId=" + achievementId +
                ", account='" + account + '\'' +
                ", examinId=" + examinId +
                ", lessonId=" + lessonId +
                ", classId=" + classId +
                ", score=" + score +
                ", examin=" + examin +
                ", lesson=" + lesson +
                ", myClass=" + myClass +
                '}';
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getExaminId() {
        return examinId;
    }

    public void setExaminId(Integer examinId) {
        this.examinId = examinId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Examin getExamin() {
        return examin;
    }

    public void setExamin(Examin examin) {
        this.examin = examin;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }
}

package com.hubu.pojo;

public class Wrong {
    private Integer wrongId;
    private Integer examinId;
    private Integer paperId;
    private String questionIds;
    private String account;
    private String options;
    private Examin examin;
    private Paper paper;

    @Override
    public String toString() {
        return "Wrong{" +
                "wrongId=" + wrongId +
                ", examinId=" + examinId +
                ", paperId=" + paperId +
                ", questionIds='" + questionIds + '\'' +
                ", account='" + account + '\'' +
                ", options='" + options + '\'' +
                ", examin=" + examin +
                ", paper=" + paper +
                '}';
    }

    public Integer getWrongId() {
        return wrongId;
    }

    public void setWrongId(Integer wrongId) {
        this.wrongId = wrongId;
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

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Examin getExamin() {
        return examin;
    }

    public void setExamin(Examin examin) {
        this.examin = examin;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}

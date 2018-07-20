package com.hubu.pojo;

import java.util.Objects;

public class Card {
    private Integer cardId;
    private Integer examinId;
    private Integer paperId;
    private String account;
    private String options;
    private Examin examin;
    private Paper paper;

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", examinId=" + examinId +
                ", paperId=" + paperId +
                ", account='" + account + '\'' +
                ", options='" + options + '\'' +
                ", examin=" + examin +
                ", paper=" + paper +
                '}';
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

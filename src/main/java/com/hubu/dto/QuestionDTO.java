package com.hubu.dto;

import com.hubu.pojo.Question;

public class QuestionDTO extends Question {
    private  int  isWrong ;
    private String  wrongKey;

    public int getIsWrong() {
        return isWrong;
    }

    public void setIsWrong(int isWrong) {
        this.isWrong = isWrong;
    }

    public String getWrongKey() {
        return wrongKey;
    }

    public void setWrongKey(String wrongKey) {
        this.wrongKey = wrongKey;
    }
}

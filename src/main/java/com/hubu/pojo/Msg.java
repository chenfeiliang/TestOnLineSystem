package com.hubu.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
@Scope("prototype")
public class Msg {
    private int code;

    private String msg;

    private Map<String,Object> extend = new HashMap<String,Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public  Msg success(){
        Msg result = new Msg();
        result.code = 200;
        result.setMsg("true");
        return  result;
    }

    public Msg fail(){
        Msg result = new Msg();
        result.code = 500;
        result.setMsg("false");
        return  result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "extend=" + extend +
                '}';
    }
}

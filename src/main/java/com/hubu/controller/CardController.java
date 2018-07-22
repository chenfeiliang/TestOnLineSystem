package com.hubu.controller;

import com.hubu.pojo.Card;
import com.hubu.pojo.Msg;
import com.hubu.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
public class CardController {

    @Autowired
    CardService cardService;


    @RequestMapping(path = "/addCard",method = {RequestMethod.POST})
    public Msg addCard(Card card , HttpSession session){
        Object object = session.getAttribute("user");
        String account = String.valueOf(object) ;
        card.setAccount(account);
        int flag = cardService.addCard(card);
        if (flag==0){
            return new Msg().fail().add("errorInfo","试卷已提交，请勿重复操作");
        }
        return  flag== 1 ? new Msg().success() : new Msg().fail();
    }
}

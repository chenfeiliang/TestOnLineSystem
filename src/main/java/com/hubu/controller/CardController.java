package com.hubu.controller;

import com.hubu.pojo.Card;
import com.hubu.pojo.Msg;
import com.hubu.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    CardService cardService;


    @RequestMapping(path = "/addCard",method = {RequestMethod.POST})
    public Msg addCard(Card card){
        card.setAccount("jiao");
        return cardService.addCard(card) == 1 ? new Msg().success() : new Msg().fail();
    }
}

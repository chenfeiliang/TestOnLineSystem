package com.hubu.service;

import com.hubu.dao.CardDAO;
import com.hubu.pojo.Card;
import com.hubu.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardDAO cardDAO;

    public Integer addCard(Card card) {
        try {
            return cardDAO.insertCard(card);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}

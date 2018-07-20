package com.hubu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardDAOTest {

    @Autowired
    CardDAO cardDAO;

    @Test
    public void insertCard() {
    }

    @Test
    public void isExit() {
      int x = cardDAO.isExit(15,"jiao3");
      System.out.println(x);
    }

    @Test
    public void updateCard() {
        int x = cardDAO.updateCard(15,"jiao3","-1,-1,-1,-1,-1,-1,-1");
        System.out.println(x);
    }
}
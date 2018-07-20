package com.hubu.service;

import com.hubu.pojo.Examin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {

    @Autowired
    ExamService examService;

    @Autowired
    HomeService homeService;
    @Test
    public void getExamByAccount() {
        List<Map> examins = homeService.getExamByAccount("jiao3");
        System.out.println(examins.size());
    }
}
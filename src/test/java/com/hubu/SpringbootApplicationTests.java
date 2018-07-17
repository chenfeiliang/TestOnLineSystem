package com.hubu;

import com.hubu.dao.ClassDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	ClassDAO classDAO;
	@Test
	public void contextLoads() {
	}

	@Test
	public void t() {
		int currentPage = 5;
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
		int[] result = new int [5];
		int num = 0;
		for (int i = 0; i < 2; i++) {
			if (!list.contains(currentPage--))
				break;
			--num;
		}
		for (int i = 0; i < result.length; i++) {
			result[i] = currentPage + num++;
		}
		System.out.println(Arrays.toString(result));

	}
}

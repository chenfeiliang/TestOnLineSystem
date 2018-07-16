package com.hubu.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Myutils {
    public static int[] pageCount(Integer currentPage,int[] nums){
        if (nums.length < 5)
            return nums;
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int[] result = new int [5];
        int temp = currentPage;
        int num1 = 0;
        for (int i = 0; i < result.length/2; i++) {
            if (!list.contains(--temp))
                break;
            --num1;
        }
        if (currentPage >= nums[nums.length-1] - result.length/2){
            int num2 = 0;
            for (int i = nums.length-5; i < nums.length; i++) {
                result[num2++] = nums[i];
            }
        }else {
            for (int i = 0; i < result.length; i++) {
                result[i] = currentPage + num1++;
            }
        }
        return result;
    }
}

package com.hubu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Myutils {
    private static final Logger logger = LoggerFactory.getLogger(Myutils.class);
    public static int[] pageCount(Integer currentPage,int[] nums){
        if (nums.length < 5)
            return nums;
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int[] result = new int [5];
        int temp = currentPage;
        int num1 = 0;
        if (currentPage >= nums[nums.length-1] - result.length/2){
            int num2 = 0;
            for (int i = nums.length-result.length; i < nums.length; i++) {
                result[num2++] = nums[i];
            }
        }else {
            for (int i = 0; i < result.length/2; i++) {
                if (!list.contains(--temp))
                    break;
                --num1;
            }
            for (int i = 0; i < result.length; i++) {
                result[i] = currentPage + num1++;
            }
        }
        return result;
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("生成MD5失败", e);
            return null;
        }
    }
}

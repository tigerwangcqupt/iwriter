package com.yryz.writer.modules.bank.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDCardValidate {

    private static final Logger logger = LoggerFactory.getLogger(IDCardValidate.class);

    public static boolean validate(String no)
    {
        try{
            // 对身份证号进行长度等简单判断
            if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]"))
            {
                return false;
            }
            // 1-17位相乘因子数组
            int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
            // 18位随机码数组
            char[] random = "10X98765432".toCharArray();
            // 计算1-17位与相应因子乘积之和
            int total = 0;
            for (int i = 0; i < 17; i++)
            {
                total += Character.getNumericValue(no.charAt(i)) * factor[i];
            }
            // 判断随机码是否相等
            return random[total % 11] == no.charAt(17);
        }catch (Exception e){
            logger.error("身份证验证失败",e);
        }
        return  false;

    }

    public static void main(String[] args)
    {
        // 正确
        System.out.println(validate("432831196411150810"));
        // 错误
        System.out.println(validate("432831196411150813xxxxx"));
    }
}

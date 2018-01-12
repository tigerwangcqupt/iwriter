package com.yryz.writer.modules.profit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    /**
     * 判断是否是正整数
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        Integer leap = Integer.parseInt(str);
        if(leap<=0){
            return false;
        }
        return true;
    }

    /**
     * 判断是否是正整数
     * @param str
     * @return
     */
    public static boolean checkIntNumber(String str) {
        String rex = "^\\+?[1-9][0-9]*$";
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(str);
        if(m.find()){
            return true;
        }
        return false;
    }
}

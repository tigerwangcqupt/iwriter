package com.yryz.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

    public static boolean matcher(Object obj, String regEx) {
        if(obj == null || regEx == null){
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(obj.toString());
        return matcher.matches();
    }

}

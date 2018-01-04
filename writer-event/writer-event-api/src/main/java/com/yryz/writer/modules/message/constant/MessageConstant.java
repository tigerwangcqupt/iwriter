package com.yryz.writer.modules.message.constant;

import org.apache.commons.lang.StringUtils;

/**
 * File Name: MessageConstant
 * Package Name: com.yryz.writer.modules.message.constant
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 15:29
 **/
public class MessageConstant {

    /** 评论数 */
    public static String COMMENT_COUNT = "comment_count";

    /** 收藏数 */
    public static String FAVORITE_COUNT = "favorite_count";

    /** 分享数 */
    public static String SHARE_COUNT = "share_count";

    /** 分享数 */
    public static String NOTICE_COUNT = "notice_count";

    /**
     * 默认的key
     * */
    public static String DEFAULT_KEY = "yryzwriter_message";

    /**
     * 获取hash中的key值
//     * @param   moduleEnum
     * @param   writerId
     * @return
     * */
    public static String getHashKey( Long writerId) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DEFAULT_KEY);
        stringBuffer.append("_");
//        stringBuffer.append(StringUtils.isNotBlank(moduleEnum) ? moduleEnum : "");
//        stringBuffer.append("_");
        stringBuffer.append(writerId != null ? writerId : 0L);

        return stringBuffer.toString();
    }

    public static String getHashField(String moduleEnum) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(DEFAULT_KEY);
//        stringBuffer.append("_");
//        stringBuffer.append(StringUtils.isNotBlank(moduleEnum) ? moduleEnum : "");


        return StringUtils.isNotBlank(moduleEnum) ? moduleEnum : "";
    }

}

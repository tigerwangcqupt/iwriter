package com.yryz.writer.modules.message.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * File Name: ModuleEnumConstants
 * Package Name: com.yryz.writer.modules.message.constant
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 18:02
 **/
public class ModuleEnumConstants {

    /**
     * 通知
     */
    public static final String NOTICE = ModuleEnum.NOTICE.getId();

    /**
     * 评论
     */
    public static final String COMMENT = ModuleEnum.COMMENT.getId();

    /**
     * 分享
     */
    public static final String SHARE = ModuleEnum.SHARE.getId();

    /**
     * 收藏
     */
    public static final String FAVORITE = ModuleEnum.FAVORITE.getId();

    /**
     * 平台
     */
    public static final String PLATFORM = ModuleEnum.PLATFORM.getId();

    /**
     * 收益
     */
    public static final String INCOME = ModuleEnum.INCOME.getId();

    /**
     * 粉丝
     */
    public static final String FANS = ModuleEnum.FANS.getId();

    //全部ModuleEnum对象
    public static final Map<String, ModuleEnum> moduleEnumMap = new HashMap<>();

    static {
        if(ModuleEnum.values() != null && ModuleEnum.values().length > 0){
            for(ModuleEnum e : ModuleEnum.values()){
                moduleEnumMap.put(e.getId(), e);
            }
        }
    }
}

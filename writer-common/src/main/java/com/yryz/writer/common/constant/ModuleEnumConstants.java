package com.yryz.writer.common.constant;

import java.util.HashMap;
import java.util.Map;

public class ModuleEnumConstants {

    //线下英文沙龙
    public static final String SALON_EN_ONLINE = "10010";
    //线下英文沙龙
    public static final String SALON_EN_OFFLINE = ModuleEnum.SALON_EN_OFFLINE.getId();
    //线上中文沙龙
    public static final String SALON_ZH_ONLINE = ModuleEnum.SALON_ZH_ONLINE.getId();
    //线下中文沙龙
    public static final String SALON_ZH_OFFLINE = ModuleEnum.SALON_ZH_OFFLINE.getId();
    //沙龙报道
    public static final String SALON_REPORT = ModuleEnum.SALON_REPORT.getId();
    //语言文化
    public static final String LANGUAGE_CULTURE = ModuleEnum.LANGUAGE_CULTURE.getId();
    //Q&A问题
    public static final String QUESTION = ModuleEnum.QUESTION.getId();
    //Q&A答案
    public static final String ANSWER = ModuleEnum.ANSWER.getId();
    //翻译
    public static final String TRANSLATE = ModuleEnum.TRANSLATE.getId();
    //活动
    public static final String ACTIVITY = ModuleEnum.ACTIVITY.getId();
    //工作机会
    public static final String JOBS = ModuleEnum.JOBS.getId();
    //朋友圈
    public static final String MOMENTS = ModuleEnum.MOMENTS.getId();
    //消息公告
    public static final String NOTICE = ModuleEnum.NOTICE.getId();
    //我的服务
    public static final String MY_SERVICE = ModuleEnum.MY_SERVICE.getId();
    //我的收藏
    public static final String MY_FAVORITE = ModuleEnum.MY_FAVORITE.getId();
    //举报用户
    public static final String INFORM_USER = ModuleEnum.INFORM_USER.getId();
    //帮助与反馈
    public static final String FEEDBACK = ModuleEnum.FEEDBACK.getId();
    //公用配置
    public static final String CONFIGS= ModuleEnum.CONFIGS.getId();
    //群组管理
    public static final String GROUPS= ModuleEnum.GROUPS.getId();
    //违规记录
    public static final String VIOLATION_RECORD=ModuleEnum.VIOLATION_RECORD.getId();

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

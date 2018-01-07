package com.yryz.writer.common.constant;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2017/11/17 17:18
 * Created by lifan
 * 功能枚举类
 */
public enum ModuleEnum {

    SALON_EN_OFFLINE("10011",
            "线下英文沙龙",
            "English Salon",
            "com.yryz.qsource.modules.salon.SalonForAdminApi",
            true,
            false,
            false),

    SALON_ZH_ONLINE("10012",
            "线上中文沙龙",
            "Chinese Salon",
            "com.yryz.qsource.modules.salon.SalonForAdminApi",
            true,
            false,
            true),

    SALON_ZH_OFFLINE("10013",
            "线下中文沙龙",
            "Chinese Salon",
            "com.yryz.qsource.modules.salon.SalonForAdminApi",
            true,
            false,
            false),

    SALON_REPORT("10014",
            "线下中文沙龙报道",
            "Chinese Salon",
            "com.yryz.qsource.modules.salon.SalonForAdminApi",
            true,
            false,
            true),

    LANGUAGE_CULTURE("10015",
            "语言文化",
            "Language&Culture",
            "com.yryz.qsource.modules.culture.CultureApi",
            true,
            false,
            true),

    QUESTION("10016",
            "Q&A问题",
            "Q&A",
            "com.yryz.qsource.modules.question.QuestionApi",
            true,
            true,
            false),

    ANSWER("10017",
            "Q&A答案",
            "Q&A",
            "com.yryz.qsource.modules.answer.AnswerApi",
            true,
            true,
            true),

    TRANSLATE("10018",
            "翻译",
            "Translation",
            "",
            false,
            false,
            false),

    ACTIVITY("10019",
            "活动",
            "Events",
            "com.yryz.qsource.modules.activity.ActivityApi",
            true,
            true,
            false),

    JOBS("10020",
            "工作机会",
            "Jobs",
            "com.yryz.qsource.modules.jobs.JobsApi",
            true,
            true,
            false),

    MOMENTS("10021",
            "朋友圈",
            "Moments",
            "",
            false,
            false,
            false),

    NOTICE("10022",
            "消息公告",
            "Notice",
            "",
            false,
            false,
            false),

    MY_SERVICE("10023",
            "我的服务",
            "MY Service",
            "",
            false,
            false,
            false),

    MY_FAVORITE("10024",
            "我的收藏",
            "My Favorite",
            "",
            false,
            false,
            false),

    INFORM_USER("10025",
            "举报用户",
            "Inform User",
            "",
            false,
            false,
            false),

    FEEDBACK("10026",
            "帮助与反馈",
            "Help & Feedback",
            "",
            false,
            false,
            false),

    //--------自定义枚举ID，无具体业务相关----------
    CONFIGS("20026",
            "公用配置",
            "Config",
            "",
            false,
            false,
            false),

    GROUPS("20027",
            "群管理",
            "Group",
            "",
            false,
            false,
            false),

    VIOLATION_RECORD("20028",
            "违规记录",
            "violation record",
            "",
            false,
            false,
            false)
    //--------自定义枚举ID，无具体业务相关----------
    ;

    //编码
    private String id;

    //中文名字
    private String zhName;

    //英文名字
    private String enName;

    //接口路径
    private String path;

    //是否支持举报
    private boolean whetherInform;

    //是否需要同步统计数据
    private boolean whetherSyncEvent;

    //是否支持评论
    private boolean whetherComment;

    ModuleEnum(String id, String zhName, String enName, String path, boolean whetherInform, boolean whetherSyncEvent, boolean whetherComment) {
        this.id = id;
        this.zhName = zhName;
        this.enName = enName;
        this.path = path;
        this.whetherInform = whetherInform;
        this.whetherSyncEvent = whetherSyncEvent;
        this.whetherComment = whetherComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isWhetherInform() {
        return whetherInform;
    }

    public void setWhetherInform(boolean whetherInform) {
        this.whetherInform = whetherInform;
    }

    public boolean isWhetherSyncEvent() {
        return whetherSyncEvent;
    }

    public void setWhetherSyncEvent(boolean whetherSyncEvent) {
        this.whetherSyncEvent = whetherSyncEvent;
    }

    public boolean isWhetherComment() {
        return whetherComment;
    }

    public void setWhetherComment(boolean whetherComment) {
        this.whetherComment = whetherComment;
    }
}

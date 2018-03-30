package com.yryz.writer.modules.message.constant;

/**
 * File Name: ModuleEnum
 * Package Name: com.yryz.writer.modules.message.constant
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 10:17
 **/
public enum ModuleEnum {
    /** 通知 */
    INDEX("10000",
            "首页",
            "index",
            "/index"),
    /** 通知 */
    NOTICE("10001",
            "通知",
            "notice",
            "/service/noticeList"),

    /** 评论 */
    COMMENT("10002",
                   "评论",
            "comment",
            "/service/noticeList"),

    /** 分享 */
    SHARE("10003",
            "分享",
            "share",
            "/service/noticeList"),

    /** 收藏 */
    FAVORITE("10004",
            "收藏",
            "favorite",
            "/service/noticeList"),

    /** 平台 */
    PLATFORM("10005",
            "平台任务",
            "platform",
            "/task"),

    /** 收益 */
    INCOME("10006",
            "收益",
            "income",
            "/income"),

    /** 粉丝 */
    FANS("10007",
            "粉丝",
            "fans",
            "/fans"),

    /** 活动 */
    ACTIVITY("10008",
            "活动",
            "activity",
            "/activity")
    ;

    //编码
    private String id;

    //中文名字
    private String name;

    //英文值
    private String value;

    /**
     * 访问路径
     */
    private String url;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    ModuleEnum(String id, String name, String value, String url) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.url = url;
    }
}

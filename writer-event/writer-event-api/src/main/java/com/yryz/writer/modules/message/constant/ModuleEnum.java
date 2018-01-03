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
    NOTICE("10001",
            "通知"),

    COMMENT("10002",
                   "评论"),
    SHARE("10003",
            "分享"),
    FAVORITE("10004",
                   "收藏")
    ;

    //编码
    private String id;

    //中文名字
    private String zhName;

    public String getId() {
        return id;
    }

    public String getZhName() {
        return zhName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    ModuleEnum(String id, String zhName) {
        this.id = id;
        this.zhName = zhName;
    }
}

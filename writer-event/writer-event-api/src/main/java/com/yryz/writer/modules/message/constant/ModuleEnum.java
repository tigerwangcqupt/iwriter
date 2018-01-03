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
    private String name;

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

    ModuleEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

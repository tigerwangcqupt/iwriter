package com.yryz.writer.modules.articleclassify;

/**
 * File Name: ArticleClassifyConstant
 * Package Name: com.yryz.writer.modules.articleclassify
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-10 19:31
 **/
public class ArticleClassifyConstant {

    //末级分类
    public static final int LAST_STAGE_YES = 0;
    //非末级分类
    public static final int LAST_STAGE_NO = 1;

    /**
     * 推荐
     */
    public static final byte RECOMMEND_YES = 1;

    /**
     * 不推荐
     */
    public static final byte RECOMMEND_NO = 0;

    /**
     * 上架
     */
    public static final byte SHELVE_YES = 0;

    /**
     * 下架
     */
    public static final byte SHELVE_NO = 1;

    /**
     * 删除
     */
    public static final byte DELETE_YES = 1;

    /**
     * 未删除
     */
    public static final byte DELETE_NO = 0;

    /**
     * 默认权重
     */
    public static final int DEFAULT_SORT = 9999;

}

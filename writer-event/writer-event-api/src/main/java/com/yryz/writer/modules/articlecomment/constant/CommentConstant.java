package com.yryz.writer.modules.articlecomment.constant;

import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.message.constant.MessageConstant;

/**
 * File Name: CommentConstant
 * Package Name: com.yryz.writer.modules.articlecomment.constant
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 16:01
 **/
public class CommentConstant {

    /**
     * 获取hash中的key值
     * @return
     * */
    public static String getHashKey(ArticleComment articleComment) {
        return MessageConstant.getHashKey(articleComment.getWriterId());
    }
}

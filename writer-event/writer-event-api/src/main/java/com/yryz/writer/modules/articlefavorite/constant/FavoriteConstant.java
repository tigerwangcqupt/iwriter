package com.yryz.writer.modules.articlefavorite.constant;

import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.message.constant.MessageConstant;
import com.yryz.writer.modules.message.constant.ModuleEnum;

/**
 * File Name: FavoriteConstant
 * Package Name: com.yryz.writer.modules.articlefavorite.constant
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 15:53
 **/
public class FavoriteConstant {

    /**
     * 获取hash中的key值
     * @return
     * */
    public static String getHashKey(ArticleFavorite articleFavorite) {
        return MessageConstant.getHashKey(articleFavorite.getWriterId());
    }
}

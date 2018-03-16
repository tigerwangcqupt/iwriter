package com.yryz.writer.modules.articlearticleclassify;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.article.Article;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: created In 19:25 2018/3/15
 * @Modified By:
 */
public interface AppArticleArticleClassifyApi {
    RpcResponse<List<Article>> getArticleByStageClassifyId(Long Id, Integer systemType, Integer pageNo, Integer pageSize);
}

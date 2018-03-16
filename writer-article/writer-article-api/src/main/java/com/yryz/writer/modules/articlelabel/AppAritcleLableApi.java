package com.yryz.writer.modules.articlelabel;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: created In 13:38 2018/3/15
 * @Modified By:
 */
public interface AppAritcleLableApi {

    /**
     *  热门标签
     *
     *  @return
     * */
    RpcResponse<List<ArticleLabelVo>> getHotArticleLabel();


    /**
     *  标签(文章)详情查询（根据标签id）
     *  @param articleLabelId
     *  @return
     * */
    RpcResponse<ArticleLabelVo> detail(Long articleLabelId);

    /**
     *  根据标签查询文章
     *  @param labelId
     *  @param systemType
     *  @param pageNo
     *  @param pageSize
     *  @return
     * */
    RpcResponse<List<Article>> getArticleByArticleLabelId(Long labelId,Integer systemType,Integer pageNo,Integer pageSize);
}

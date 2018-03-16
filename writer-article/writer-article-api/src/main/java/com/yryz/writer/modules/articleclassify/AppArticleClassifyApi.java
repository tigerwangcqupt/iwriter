package com.yryz.writer.modules.articleclassify;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: created In 11:58 2018/3/15
 * @Modified By:
 */
public interface AppArticleClassifyApi {
    /**
     *  分类(文章) 列表查询
     *  排序 *推荐【权重升序】，未推荐【更新时间倒叙】
     *  @return
     * */
    RpcResponse<List<ArticleClassifyVo>> getArticleClassifys(Long articleClassifyId);

    /**
     *  根据分类ids 查找文章的ids
     *  @param articleClassifyId 分类id
     *
     *  @return
     * */
    RpcResponse<List<Long>> getArticleClassifyIds(Long articleClassifyId);

    /**
     *  根据id查询分类
     *  @param articleClassifyId 分类id
     *
     *  @return
     * */
    RpcResponse<ArticleClassify> get(Long articleClassifyId);

}

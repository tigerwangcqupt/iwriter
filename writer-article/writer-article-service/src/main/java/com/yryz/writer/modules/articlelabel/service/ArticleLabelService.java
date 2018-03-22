package com.yryz.writer.modules.articlelabel.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * 
  * @ClassName: ArticleLabelService
  * @Description: ArticleLabel业务访问接口
  * @author huyangyang
  * @date 2018-01-11 19:29:39
  *
 */
@Repository
public interface ArticleLabelService extends BaseService {

   /**
    * 文章标签分页查询
    * @param articleLabelDto
    * @return
    */
   PageList<ArticleLabelVo> selectList(ArticleLabelDto articleLabelDto);

   /**
    * 文章标签详情
    * @param articleLabelId
    * @return
    */
   ArticleLabelVo detail(Long articleLabelId);

   /**
    * 新增
    * @param articleLabel     文章分类
    * */
   Boolean insert(ArticleLabel articleLabel);

   /**
    * 更新
    * @param articleLabel     文章分类
    * */
   Boolean update(ArticleLabel articleLabel);

   /**
    * 上架标签
    * @param articleLabelId
    * @return
    */
   Boolean shelveOn(Long articleLabelId);

   /**
    * 下架标签
    * @param articleLabelId
    * @return
    */
   Boolean shelveOff(Long articleLabelId);

   /**
    * 删除标签
    * @param kid  分类id
    * @return
    */
   Boolean deleteArticleLabel(Long kid, String lastUpdateUserId);

   /**
    * 恢复标签
    * @param kid  分类id
    * @return
    */
   Boolean recoverArticleLabel(Long kid, String lastUpdateUserId);

   /**
    * 校验标签（是否关联了文章）
    * @param kid
    * @return
    */
   Boolean validateLabel(Long kid);

   /**
    * 交换权重
    * @param id
    * @param tid
    * @return
    */
   Boolean setSort(Long id, Long tid);

   /**
    * 设置推荐/取消推荐
    * @param id
    *
    * @return
    */
   Boolean setRecommend(Long id,Integer flag);

   /**
    * 查询热门标签
    *
    * @return
    */
   List<ArticleLabelVo> getHotArticleLabel();

   /**
    * 根据标签查询文章
    *
    * @return
    */
   List<Article> getArticleByArticleLabelId(Long lableId,Integer systemType,Integer pageNo,Integer pageSize);

   /**
    * 文章标签推荐列表
    *
    * @return
    */
   PageList<ArticleLabelVo> recommendlist(ArticleLabelDto articleLabelDto);

   /**
    * 文章标签推荐列表
    *
    * @return
    */
   Long getUpOrDownRecommend(Long lableId,Integer flag);
}
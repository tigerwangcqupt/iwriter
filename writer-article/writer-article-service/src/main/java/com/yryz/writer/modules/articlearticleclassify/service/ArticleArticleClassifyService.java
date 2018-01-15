package com.yryz.writer.modules.articlearticleclassify.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;
import com.yryz.writer.modules.articlearticleclassify.vo.ArticleArticleClassifyVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * 
  * @ClassName: ArticleArticleClassifyService
  * @Description: ArticleArticleClassify业务访问接口
  * @author huyangyang
  * @date 2018-01-15 10:34:38
  *
 */
@Repository
public interface ArticleArticleClassifyService extends BaseService {

   PageList<ArticleArticleClassifyVo> selectList(ArticleArticleClassifyDto articleArticleClassifyDto);

   ArticleArticleClassifyVo detail(Long articleArticleClassifyId);

   /**
    * 根据分类查询文章
    * @param articleArticleClassifyDto
    * @return
    */
   PageList<ArticleArticleClassifyVo> queryByClassifyId(ArticleArticleClassifyDto articleArticleClassifyDto);
}
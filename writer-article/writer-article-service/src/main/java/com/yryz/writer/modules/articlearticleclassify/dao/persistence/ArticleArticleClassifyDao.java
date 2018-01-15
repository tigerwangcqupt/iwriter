package com.yryz.writer.modules.articlearticleclassify.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleArticleClassifyDao
  * @Description: ArticleArticleClassify数据访问接口
  * @author huyangyang
  * @date 2018-01-15 10:34:38
  *
 */
@Repository
public interface ArticleArticleClassifyDao extends BaseDao {

    List<ArticleArticleClassify> selectList(ArticleArticleClassifyDto articleArticleClassifyDto);

    /**
     * 根据分类查询文章
     * @param classifyId
     * @return
     */
    List<ArticleArticleClassify> queryByClassifyId(Long classifyId);
}
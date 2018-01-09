package com.yryz.writer.modules.articleClassify.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.articleClassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleClassify.entity.ArticleClassify;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleClassifyDao
  * @Description: ArticleClassify数据访问接口
  * @author huyangyang
  * @date 2018-01-09 11:03:59
  *
 */
@Repository
public interface ArticleClassifyDao extends BaseDao {

    /**
     * 新增
     * @param articleClassify     文章分类
     * */
    int insert(ArticleClassify articleClassify);

    /**
     * 文章分类分页
     * @param articleClassifyDto
     * @return
     */
    List<ArticleClassify> selectList(ArticleClassifyDto articleClassifyDto);

}
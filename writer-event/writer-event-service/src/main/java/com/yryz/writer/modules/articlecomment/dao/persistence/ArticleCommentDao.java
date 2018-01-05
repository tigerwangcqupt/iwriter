package com.yryz.writer.modules.articlecomment.dao.persistence;

import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleCommentDao
  * @Description: ArticleComment数据访问接口
  * @author huyangyang
  * @date 2018-01-03 11:43:39
  *
 */
@Repository
public interface ArticleCommentDao extends BaseDao {

    List<ArticleComment> selectList(ArticleCommentDto articleCommentDto);

    /**
     * 查询写手的全部文章的用户评论
     * @param articleCommentDto
     * @return
     */
    List<ArticleComment> selectListByWriter(ArticleCommentDto articleCommentDto);


}
package com.yryz.writer.modules.articlefavorite.dao.persistence;

import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleFavoriteDao
  * @Description: ArticleFavorite数据访问接口
  * @author huyangyang
  * @date 2018-01-02 20:52:42
  *
 */
@Repository
public interface ArticleFavoriteDao extends BaseDao {

    List<ArticleFavorite> selectList(ArticleFavoriteDto articleFavoriteDto);

    /**
     * 查询写手文章的全部收藏详情
     * @param articleFavoriteDto
     * @return
     */
    List<ArticleFavorite> selectListByWriter(ArticleFavoriteDto articleFavoriteDto);
}
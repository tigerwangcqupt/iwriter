package com.yryz.writer.modules.articlelabel.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleLabelDao
  * @Description: ArticleLabel数据访问接口
  * @author huyangyang
  * @date 2018-01-11 19:29:39
  *
 */
@Repository
public interface ArticleLabelDao extends BaseDao {

    List<ArticleLabel> selectList(ArticleLabelDto articleLabelDto);

    /**
     * 逻辑删除文章分类
     * @param kid
     * @param lastUpdateUserId
     * @return
     */
    int deleteArticleLabel(@Param("kid") Long kid, @Param("lastUpdateUserId") String lastUpdateUserId);

    /**
     * 恢复文章分类
     * @param kid
     * @param lastUpdateUserId
     * @return
     */
    int recoverArticleLabel(@Param("kid") Long kid, @Param("lastUpdateUserId") String lastUpdateUserId);

    /**
     * 根据标签id查询关联文章个数
     * @param kid
     * @return
     */
    int countArticleByLabelId(@Param("kid") Long kid);

    /**
     * 获取热门标签
     *
     * @return
     */
    List<ArticleLabel> getHotArticleLabel();

    /**
     * 根据标签查询文章
     * @param lableId
     * @param systemType
     * @param firstRecord
     * @param pageSize
     * @return
     */
    List<Article> getArticleByArticleLabelId(@Param("lableId") Long lableId,@Param("systemType") Integer systemType,@Param("firstRecord") Integer firstRecord,@Param("pageSize") Integer pageSize);

    /**
     * 根据当前最大排序数
     *
     * @return
     */
    Integer selectMaxSort();

    /**
     * 文章标签推荐列表
     *
     * @return
     */
    List<ArticleLabel> recommendlist(ArticleLabelDto articleLabelDto);

    /**
     * 根据sort查询大于或者小于的推荐分类
     * @return
     */
    List<ArticleLabel> selectSortsByRecommend(@Param("sort") Integer sort, @Param("flag") Integer flag);

    /**
     * 获取推荐数
     * @return
     */
    Integer getRecommendCount();
}
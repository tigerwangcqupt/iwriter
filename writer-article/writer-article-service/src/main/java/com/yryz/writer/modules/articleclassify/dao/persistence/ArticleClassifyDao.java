package com.yryz.writer.modules.articleclassify.dao.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;

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
     * 更新
     * @param articleClassify     文章分类
     * */
    int update(ArticleClassify articleClassify);

    /**
     * 文章分类分页
     * @param articleClassifyDto
     * @return
     */
    List<ArticleClassify> selectList(ArticleClassifyDto articleClassifyDto);

    /**
     *查询已上架的子分类个数
     * @param id
     * @return
     */
    int selectShelveOnChildCount(Long id);

    /**
     * 逻辑删除文章分类
     * @param kid
     * @param lastUpdateUserId
     * @return
     */
    int deleteArticleClassify(@Param("kid") Long kid, @Param("lastUpdateUserId") String lastUpdateUserId);

    /**
     * 恢复文章分类
     * @param kid
     * @param lastUpdateUserId
     * @return
     */
    int recoverClassify(@Param("kid") Long kid, @Param("lastUpdateUserId") String lastUpdateUserId);

    /**
     *分类下是否包含文章个数
     * @param articleClassifyId
     * @return
     */
    int countArticleByClassifyId(@Param("articleClassifyId")Long articleClassifyId);

    List<Long> queryArticleClassifyIds(Long classifyId);

    List<ArticleClassify> getArticleClassifysById(@Param("articleClassifyId")Long classifyId);

    /**
     * 查询分类推荐的最大排序值
     *
     * @return
     */
    Integer selectMaxSort();

    /**
     * 推荐列表
     *
     * @return
     */
    List<ArticleClassify> recommendlist(ArticleClassifyDto articleClassifyDto);

    /**
     * 根据sort查询大于或者小于的推荐分类
     * @return
     */
    List<ArticleClassify> selectSortsByRecommend(@Param("sort") Integer sort,@Param("flag") Integer flag);
    
    List<ArticleClassify> selectByCondition(ArticleClassify condition);
}
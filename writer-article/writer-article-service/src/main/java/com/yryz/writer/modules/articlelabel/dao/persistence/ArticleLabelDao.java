package com.yryz.writer.modules.articlelabel.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
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
}
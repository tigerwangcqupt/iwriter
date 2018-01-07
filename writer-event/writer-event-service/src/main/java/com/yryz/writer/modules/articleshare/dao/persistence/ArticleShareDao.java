package com.yryz.writer.modules.articleshare.dao.persistence;

import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ArticleShareDao
  * @Description: ArticleShare数据访问接口
  * @author huyangyang
  * @date 2018-01-02 20:24:27
  *
 */
@Repository
public interface ArticleShareDao extends BaseDao {

    List<ArticleShare> selectList(ArticleShareDto articleShareDto);

    /**
     * 查询写手文章的全部分享详情
     * @param articleShareDto
     * @return
     */
    List<ArticleShare> selectListByWriter(ArticleShareDto articleShareDto);
}
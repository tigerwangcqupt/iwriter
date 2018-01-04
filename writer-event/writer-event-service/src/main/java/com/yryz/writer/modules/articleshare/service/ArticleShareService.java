package com.yryz.writer.modules.articleshare.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: ArticleShareService
  * @Description: ArticleShare业务访问接口
  * @author huyangyang
  * @date 2018-01-02 20:24:27
  *
 */
@Repository
public interface ArticleShareService extends BaseService {

   PageList<ArticleShareVo> selectList(ArticleShareDto articleShareDto);

   ArticleShareVo detail(Long articleShareId);

   /**
    * 保存
    * @param articleShare
    * @return
    */
   Long saveArticleShare(ArticleShare articleShare);

   /**
    * 写手的文章分享列表
    * @param articleShareDto
    * @return
    */
   public PageList<ArticleShareVo> selectListByWriter(ArticleShareDto articleShareDto);
}
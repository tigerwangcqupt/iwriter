package com.yryz.writer.modules.articleClassify.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.articleClassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleClassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleClassify.vo.ArticleClassifyVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: ArticleClassifyService
  * @Description: ArticleClassify业务访问接口
  * @author huyangyang
  * @date 2018-01-09 11:03:59
  *
 */
@Repository
public interface ArticleClassifyService extends BaseService {

   PageList<ArticleClassifyVo> selectList(ArticleClassifyDto articleClassifyDto);

   ArticleClassifyVo detail(Long articleClassifyId);

   /**
    * 新增
    * @param articleClassify     文章分类
    * */
   Boolean insert(ArticleClassify articleClassify);
}
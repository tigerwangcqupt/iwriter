package com.yryz.writer.modules.articleclassify.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;
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

   /**
    * 更新
    * @param articleClassify     文章分类
    * */
   Boolean update(ArticleClassify articleClassify);

   /**
    * 上架分类
    * @param articleClassifyId
    * @return
    */
   Boolean shelveOn(Long articleClassifyId);

   /**
    * 下架分类
    * @param articleClassifyId
    * @return
    */
   Boolean shelveOff(Long articleClassifyId);

   /**
    * 删除分类
    * @param kid  分类id
    * @return
    */
   Boolean deleteArticleClassify(Long kid, String lastUpdateUserId);

   /**
    * 恢复分类
    * @param kid  分类id
    * @return
    */
   Boolean recoverArticleClassify(Long kid, String lastUpdateUserId);

   /**
    * 检查该分类是否可以作为父类
    * 即该分类下是否包含文章
    * 若有文章就是末级分类 不能以它做父类
    * @param kid
    * @return
    */
   Boolean checkArticleClassify(Long kid);
}
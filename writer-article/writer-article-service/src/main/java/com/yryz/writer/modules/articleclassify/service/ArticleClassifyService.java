package com.yryz.writer.modules.articleclassify.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

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

   /**
    * 级联查询分类id
    * @param id
    * @return
    */
   List<Long> getArticleClassifyIds(Long id);

   /**
    * 分类列表（classifyId==0 查询一级菜单）
    * @param
    * @return
    */
   List<ArticleClassifyVo> getArticleClassifys(Long classifyId);

   /**
    * 交换权重
    * @param id
    * @param tid
    *
    * @return
    */
   Boolean setSort(Long id, Long tid);

   /**
    * 设置推荐/取消推荐
    * @param id
    *
    * @return
    */
   Boolean setRecommend(Long id,Integer flag);

   /**
    * 推荐列表
    * @param articleClassifyDto
    *
    * @return
    */
   PageList<ArticleClassifyVo> recommendlist(ArticleClassifyDto articleClassifyDto);
}
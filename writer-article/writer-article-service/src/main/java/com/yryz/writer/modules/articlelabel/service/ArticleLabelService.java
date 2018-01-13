package com.yryz.writer.modules.articlelabel.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: ArticleLabelService
  * @Description: ArticleLabel业务访问接口
  * @author huyangyang
  * @date 2018-01-11 19:29:39
  *
 */
@Repository
public interface ArticleLabelService extends BaseService {

   PageList<ArticleLabelVo> selectList(ArticleLabelDto articleLabelDto);

   ArticleLabelVo detail(Long articleLabelId);

   /**
    * 新增
    * @param articleLabel     文章分类
    * */
   Boolean insert(ArticleLabel articleLabel);

   /**
    * 更新
    * @param articleLabel     文章分类
    * */
   Boolean update(ArticleLabel articleLabel);

   /**
    * 上架标签
    * @param articleLabelId
    * @return
    */
   Boolean shelveOn(Long articleLabelId);

   /**
    * 下架标签
    * @param articleLabelId
    * @return
    */
   Boolean shelveOff(Long articleLabelId);

   /**
    * 删除标签
    * @param kid  分类id
    * @return
    */
   Boolean deleteArticleLabel(Long kid, String lastUpdateUserId);

   /**
    * 恢复标签
    * @param kid  分类id
    * @return
    */
   Boolean recoverArticleLabel(Long kid, String lastUpdateUserId);

   /**
    * 校验标签（是否关联了文章）
    * @param kid
    * @return
    */
   Boolean validateLabel(Long kid);
}
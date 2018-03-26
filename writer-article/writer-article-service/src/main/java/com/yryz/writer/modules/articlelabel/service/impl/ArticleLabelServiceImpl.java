package com.yryz.writer.modules.articlelabel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.constant.CommonConstants;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articleclassify.constant.ArticleClassifyConstant;
import com.yryz.writer.modules.articlelabel.dao.persistence.ArticleLabelDao;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.service.ArticleLabelService;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import com.yryz.writer.modules.id.api.IdAPI;


@Service
public class ArticleLabelServiceImpl extends BaseServiceImpl implements ArticleLabelService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleLabelServiceImpl.class);

    /** 日期格式时分秒 */
    private final static SimpleDateFormat DATETIME_PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ArticleLabelDao articleLabelDao;

    @Autowired
    private IdAPI idApi;

    protected BaseDao getDao() {
        return articleLabelDao;
    }

    public PageList<ArticleLabelVo> selectList(ArticleLabelDto articleLabelDto){
        PageUtils.startPage(articleLabelDto.getCurrentPage(), articleLabelDto.getPageSize());
        List<ArticleLabel> list = articleLabelDao.selectList(articleLabelDto);
        List<ArticleLabelVo> articleLabelVoList = new ArrayList <ArticleLabelVo>();
        if(list != null && list.size() > 0) {
            for(ArticleLabel articleLabel : list){
                ArticleLabelVo articleLabelVo = new ArticleLabelVo();
                //ArticleClassify to ArticleClassifyVo
                articleLabelVo.setLabelName(articleLabel.getLabelName());
                articleLabelVo.setCreateUserId(articleLabel.getCreateUserId());
                articleLabelVo.setDelFlag(articleLabel.getDelFlag());
                articleLabelVo.setShelveFlag(articleLabel.getShelveFlag());
                articleLabelVo.setKid(articleLabel.getKid());
                articleLabelVo.setIcon(articleLabel.getIcon());
                articleLabelVo.setLabelDescription(articleLabel.getLabelDescription());
                Date createDate = articleLabel.getCreateDate();
                articleLabelVo.setCreateDate(createDate == null ? "" : DATETIME_PATTERN.format(createDate));
                //ArticleLabel to ArticleLabelVo
                articleLabelVoList.add(articleLabelVo);
            }
        }
        return new PageModel<ArticleLabelVo>().getPageList(list, articleLabelVoList);
    }


    public ArticleLabelVo detail(Long articleLabelId) {
        ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class,articleLabelId);
        if (articleLabel != null) {
            ArticleLabelVo articleLabelVo = new ArticleLabelVo();
            BeanUtils.copyProperties(articleLabel,articleLabelVo);
            return articleLabelVo;
        }
        return null;
    }

    @Override
    public Boolean insert(ArticleLabel articleLabel) {
        try {
            Assert.hasText(articleLabel.getLabelName(), "标签名称不能为空");
            Assert.hasText(articleLabel.getIcon(), "标签图标不能为空");
            Assert.hasText(articleLabel.getLabelDescription(), "标签描述不能为空");
            this.labelNameCheck(articleLabel);
            
            Long kid = idApi.getId("yryz_articlelabel");
            //设置属性
            articleLabel.setKid(kid);
            articleLabel.setSort(ArticleClassifyConstant.DEFAULT_SORT);
            articleLabel.setShelveFlag(Integer.valueOf(ArticleClassifyConstant.SHELVE_YES));
            articleLabel.setDelFlag(Integer.valueOf(ArticleClassifyConstant.DELETE_NO));

            //保存分类
            int successNum = articleLabelDao.insert(articleLabel);
            if (successNum < 1){
                return false;
            }
        }catch (Exception e){
            logger.error("新增文章标签操作失败", e);
            throw e;
        }
        return true;
    }

    @Override
    public Boolean update(ArticleLabel articleLabel) {
        try {
            this.labelNameCheck(articleLabel);
            int successNum = articleLabelDao.update(articleLabel);
            if (successNum < 1){
                return false;
            }
        }catch (Exception e){
            logger.error("更新文章标签操作失败", e);
            throw e;
        }
        return true;
    }

    @Override
    public Boolean shelveOn(Long articleLabelId) {
        try {
            Assert.notNull(articleLabelId, "标签id不能为空");
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, articleLabelId);
            if (null == articleLabel) {
                throw new IllegalArgumentException("文章标签不存在");
            }
            //上架
            articleLabel.setShelveFlag(0);
            int successNum = articleLabelDao.update(articleLabel);
            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("上架文章标签操作失败", e);
            throw e;
        }
    }

    @Override
    public Boolean shelveOff(Long articleLabelId) {
        try {
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, articleLabelId);
            if (null == articleLabel) {
                throw new IllegalArgumentException("文章标签不存在");
            }
            //上架
            articleLabel.setShelveFlag(1);
            int successNum = articleLabelDao.update(articleLabel);
            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("上架文章标签操作失败", e);
            throw e;
        }
    }

    @Override
    public Boolean deleteArticleLabel(Long kid, String lastUpdateUserId) {
        try {
            Assert.notNull(kid, "标签id不能为空");
            Assert.notNull(lastUpdateUserId, "修改人id不能为空");
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, kid);
            if (null == articleLabel) {
                throw new IllegalArgumentException("文章标签不存在");
            }
            int successNum = articleLabelDao.deleteArticleLabel(kid, lastUpdateUserId);

            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("删除文章标签操作失败", e);
            throw e;
        }
    }

    @Override
    public Boolean recoverArticleLabel(Long kid, String lastUpdateUserId) {
        try {
            Assert.notNull(kid, "标签id不能为空");
            Assert.notNull(lastUpdateUserId, "修改人id不能为空");
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, kid);
            if (null == articleLabel) {
                throw new IllegalArgumentException("文章标签不存在");
            }
            int successNum = articleLabelDao.recoverArticleLabel(kid, lastUpdateUserId);


            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("恢复文章标签操作失败", e);
            throw e;
        }
    }

    @Override
    public Boolean validateLabel(Long kid) {
        try {
            Assert.notNull(kid, "标签id不能为空");
            int count = articleLabelDao.countArticleByLabelId(kid);

            return count > 0 ? false : true;
        }catch (Exception e){
            logger.error("校验文章标签操作失败", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean setSort(Long id, Long tid) {
        try {
            // 获取被选择的数据
            ArticleLabel articleLabelId = articleLabelDao.selectByKid(ArticleLabel.class,id);
            // 获取被选择的数据的上/下一条数据
            ArticleLabel articleLabelTid = articleLabelDao.selectByKid(ArticleLabel.class,tid);
            Integer sort = articleLabelId.getSort();
            Integer tSort = articleLabelTid.getSort();
            // 交换sort
            articleLabelId.setSort(tSort);
            articleLabelTid.setSort(sort);
            articleLabelDao.update(articleLabelId);
            articleLabelDao.update(articleLabelTid);
            return true;
        } catch (Exception e) {
            logger.error("交换权重失败", e);
            throw e;
        }
    }

    @Override
    public Boolean setRecommend(Long id,Integer flag) {
        try {
            ArticleLabel articleLabel = new ArticleLabel();
            Integer sort = null;
            if(flag==1){
                sort = articleLabelDao.selectMaxSort();
                sort = sort==null?1:sort+1;
            }
            if(flag==0){
                sort = 9999;
            }
            articleLabel.setRecommendFlag(flag);
            articleLabel.setKid(id);
            articleLabel.setSort(sort);
            articleLabelDao.update(articleLabel);
            return true;
        } catch (Exception e) {
            logger.error("设置推荐失败", e);
            throw e;
        }
    }

    @Override
    public List<ArticleLabelVo> getHotArticleLabel() {
        List<ArticleLabel> list = articleLabelDao.getHotArticleLabel();
        if(!CollectionUtils.isEmpty(list)){
            List<ArticleLabelVo> listVo = new ArrayList<ArticleLabelVo>();
            list.forEach(c->{
                ArticleLabelVo vo = new ArticleLabelVo();
                BeanUtils.copyProperties(c,vo);
                listVo.add(vo);
            });
            return listVo;
        }
        return null;
    }

    @Override
    public List<Article> getArticleByArticleLabelId(Long lableId, Integer systemType, Integer pageNo, Integer pageSize) {
        Assert.notNull(lableId, "分类id不能为空");
        Assert.notNull(systemType, "设备id不能为空");
        pageNo = pageNo<1?0:pageNo;
        pageSize = pageSize<1?10:pageSize;
        return articleLabelDao.getArticleByArticleLabelId(lableId,systemType,(pageNo-1)*pageSize,pageSize);
    }

    @Override
    public PageList<ArticleLabelVo> recommendlist(ArticleLabelDto articleLabelDto) {
        PageUtils.startPage(articleLabelDto.getCurrentPage(), articleLabelDto.getPageSize());
        List<ArticleLabel> list = articleLabelDao.recommendlist(articleLabelDto);
        List<ArticleLabelVo> articleLabelVoList = null;
        if(!CollectionUtils.isEmpty(list)){
            articleLabelVoList = new ArrayList <ArticleLabelVo>();
            for(ArticleLabel ab:list){
                ArticleLabelVo articleLabelVo = new ArticleLabelVo();
                articleLabelVo.setKid(ab.getKid());
                articleLabelVo.setLabelName(ab.getLabelName());
                articleLabelVo.setSort(ab.getSort());
                articleLabelVo.setRecommendFlag(ab.getRecommendFlag());
                articleLabelVo.setDelFlag(ab.getDelFlag());
                articleLabelVo.setShelveFlag(ab.getShelveFlag());
                articleLabelVoList.add(articleLabelVo);
            }
        }
        return new PageModel<ArticleLabelVo>().getPageList(list, articleLabelVoList);
    }

    @Override
    public Long getUpOrDownRecommend(Long lableId, Integer flag) {
        Assert.notNull(lableId,"标签id不能为空");
        ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class,lableId);
        if(null==articleLabel){
            throw new YyrzPcException(ExceptionEnum.ValidateException.getCode(), "标签不存在", "标签不存在");
        }
        if(articleLabel.getRecommendFlag().equals(0)) {
            throw new YyrzPcException(ExceptionEnum.ValidateException.getCode(), "该标签没有被推荐", "该标签没有被推荐");
        }
        List<ArticleLabel> result = articleLabelDao.selectSortsByRecommend(articleLabel.getSort(),flag);
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(result)){
            ArticleLabel al = null;
            if(flag.equals(0)){
                al = result.get(result.size()-1); //向上取值
            }
            if(flag.equals(1)){  //向下取值
                al = result.get(0);
            }
            return al.getKid();
        }
        return null;
    }

    @Override
    public void labelNameCheck(ArticleLabel articleLabel) {
        // 名称去重校验
        Assert.hasText(articleLabel.getLabelName(), "LabelName is null !");
        ArticleLabel condition = new ArticleLabel();
        condition.setLabelName(articleLabel.getLabelName());
        condition.setDelFlag((int) CommonConstants.DELETE_NO);
        // condition.setShelveFlag(CommonConstants.SHELVE_YES);
        List<ArticleLabel> checkList = articleLabelDao.selectByCondition(condition);

        // 新增
        if (null == articleLabel.getKid()) {
            Assert.isTrue(CollectionUtils.isEmpty(checkList), "标签名称不能重复！");
        }
        // 编辑
        else {
            boolean checkFlag = true;
            if (org.apache.commons.collections.CollectionUtils.size(checkList) > 1) {
                checkFlag = false;
            } else if (org.apache.commons.collections.CollectionUtils.isNotEmpty(checkList)
                    && !checkList.get(0).getKid().equals(articleLabel.getKid())) {
                checkFlag = false;
            }
            Assert.isTrue(checkFlag, "标签名称不能重复！");
        }

    }
}

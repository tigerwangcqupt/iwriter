package com.yryz.writer.modules.articlelabel.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.BaseException;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.articleclassify.constant.ArticleClassifyConstant;
import com.yryz.writer.modules.id.api.IdAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.dao.persistence.ArticleLabelDao;
import com.yryz.writer.modules.articlelabel.service.ArticleLabelService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        ArticleLabelVo articleLabelVo = new ArticleLabelVo();
        if (articleLabelVo != null) {
            //ArticleLabel to ArticleLabelVo
            //ArticleClassify to ArticleClassifyVo
            articleLabelVo.setLabelName(articleLabel.getLabelName());
            articleLabelVo.setCreateUserId(articleLabel.getCreateUserId());
            articleLabelVo.setDelFlag(articleLabel.getDelFlag());
            articleLabelVo.setShelveFlag(articleLabel.getShelveFlag());
            articleLabelVo.setKid(articleLabel.getKid());
            Date createDate = articleLabel.getCreateDate();
            articleLabelVo.setCreateDate(createDate == null ? "" : DATETIME_PATTERN.format(createDate));
            articleLabelVo.setIcon(articleLabel.getIcon());
            articleLabelVo.setLabelDescription(articleLabel.getLabelDescription());
        }
        return articleLabelVo;
    }

    @Override
    public Boolean insert(ArticleLabel articleLabel) {
        try {
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
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, articleLabelId);
            if (null == articleLabel) {
                throw new BaseException("文章标签不存在");
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
                throw new BaseException("文章标签不存在");
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
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, kid);
            if (null == articleLabel) {
                throw new BaseException("文章标签不存在");
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
            ArticleLabel articleLabel = articleLabelDao.selectByKid(ArticleLabel.class, kid);
            if (null == articleLabel) {
                throw new BaseException("文章标签不存在");
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
            int count = articleLabelDao.countArticleByLabelId(kid);

            return count > 0 ? false : true;
        }catch (Exception e){
            logger.error("校验文章标签操作失败", e);
            throw e;
        }
    }
}

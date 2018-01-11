package com.yryz.writer.modules.articleclassify.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.articleclassify.ArticleClassifyConstant;
import com.yryz.writer.modules.articleclassify.dao.persistence.ArticleClassifyDao;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.service.ArticleClassifyService;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;
import com.yryz.writer.modules.id.api.IdAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章分类接口实现类
 */
@Service
public class ArticleClassifyServiceImpl extends BaseServiceImpl implements ArticleClassifyService {

    /** 日期格式时分秒 */
    private final static SimpleDateFormat DATETIME_PATTERN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(ArticleClassifyServiceImpl.class);

    @Autowired
    private ArticleClassifyDao articleClassifyDao;

    @Autowired
    private IdAPI idApi;

    protected BaseDao getDao() {
        return articleClassifyDao;
    }

    public PageList<ArticleClassifyVo> selectList(ArticleClassifyDto articleClassifyDto){
        PageUtils.startPage(articleClassifyDto.getCurrentPage(), articleClassifyDto.getPageSize());
        List<ArticleClassify> list = articleClassifyDao.selectList(articleClassifyDto);
        List<ArticleClassifyVo> articleClassifyVoList = new ArrayList <ArticleClassifyVo>();
        if(list != null && list.size() > 0) {
            for(ArticleClassify articleClassify : list){
                ArticleClassifyVo articleClassifyVo = new ArticleClassifyVo();
                //ArticleClassify to ArticleClassifyVo
                articleClassifyVo.setClassifyName(articleClassify.getClassifyName());
                articleClassifyVo.setCreateUser(articleClassify.getCreateUserNickName());
                articleClassifyVo.setCreateUserId(articleClassify.getCreateUserId());
                articleClassifyVo.setDelFlag(articleClassify.getDelFlag());
                articleClassifyVo.setParentId(articleClassify.getParentId());
                articleClassifyVo.setShelveFlag(articleClassify.getShelveFlag());
                articleClassifyVo.setKid(articleClassify.getKid());
                Date createDate = articleClassify.getCreateDate();
                articleClassifyVo.setCreateDate(createDate == null ? "" : DATETIME_PATTERN.format(articleClassify.getCreateDate()));
                articleClassifyVoList.add(articleClassifyVo);
            }
        }
        return new PageModel<ArticleClassifyVo>().getPageList(list, articleClassifyVoList);
    }


    public ArticleClassifyVo detail(Long articleClassifyId) {
        ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class,articleClassifyId);
        ArticleClassifyVo articleClassifyVo = new ArticleClassifyVo();
        if (articleClassifyVo != null) {
            //ArticleClassify to ArticleClassifyVo
            BeanUtils.copyProperties(articleClassify, articleClassifyVo);
        }
        return articleClassifyVo;
    }

    @Override
    public Boolean insert(ArticleClassify articleClassify) {
        try {
            //查父级分类
            ArticleClassify parent = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassify.getParentId());
            //父级分类是末级分类时
            if (null != parent && ArticleClassifyConstant.LAST_STAGE_YES == parent.getLastStageFlag()) {
                //父级分类为末级分类，需要修改为非末级分类
                ArticleClassify updateClassify = new ArticleClassify();
                updateClassify.setId(parent.getId());
                updateClassify.setLastStageFlag(ArticleClassifyConstant.LAST_STAGE_NO);
                updateClassify.setLastUpdateUserId(articleClassify.getLastUpdateUserId());
                articleClassifyDao.update(updateClassify);
            }
            Long kid = idApi.getId("yryz_articleclassify");
            articleClassify.setKid(kid);
            articleClassify.setRecommendFlag(Integer.valueOf(ArticleClassifyConstant.RECOMMEND_NO));
            articleClassify.setSort(ArticleClassifyConstant.DEFAULT_SORT);
            articleClassify.setLastStageFlag(ArticleClassifyConstant.LAST_STAGE_YES);
            articleClassify.setShelveFlag(Integer.valueOf(ArticleClassifyConstant.SHELVE_YES));
            articleClassify.setDelFlag(Integer.valueOf(ArticleClassifyConstant.DELETE_NO));
            //保存分类
            int successNum = articleClassifyDao.insert(articleClassify);
            if (successNum < 1){
                return false;
            }
        }catch (Exception e){
            logger.error("新增文章分类操作失败", e);
            throw e;
        }
        return true;
    }

    public Boolean update(ArticleClassify articleClassify) {
        try {
            int successNum = articleClassifyDao.update(articleClassify);
            if (successNum < 1){
                return false;
            }
        }catch (Exception e){
            logger.error("更新文章分类操作失败", e);
            throw e;
        }
        return true;
    }

    public Boolean shelveOn(Long articleClassifyId){
        try {
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassifyId);
            if (null == articleClassify) {
                throw new Exception("文章分类不存在");
            }

            if (ArticleClassifyConstant.LAST_STAGE_YES == articleClassify.getLastStageFlag()) {
                ArticleClassify parentClassify = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassify.getParentId());
                //父级分类为下架，不能下架该分类
                if(parentClassify !=null && parentClassify.getShelveFlag()==1){
                    throw new Exception("该分类的父级分类为下架，请先上架父级分类再上架该分类");
                }
            }
            //上架
            articleClassify.setLastStageFlag(0);
            int successNum = articleClassifyDao.update(articleClassify);
            return successNum > 1 ? true : false;
        }catch (Exception e){
            logger.error("上架文章分类操作失败", e);
        }
        return false;
    }

    @Override
    public Boolean shelveOff(Long articleClassifyId) {
        try {
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class,articleClassifyId);
            if (null == articleClassify) {
                throw new Exception("文章分类不存在");
            }
            //末级分类
            if (ArticleClassifyConstant.LAST_STAGE_YES != articleClassify.getLastStageFlag()) {
                //该分类下子分类存在上架的，不能直接下架
                int count = articleClassifyDao.selectShelveOnChildCount(articleClassifyId);
                if (count > 0) {
                    throw new Exception("该分类下存在上架子分类，请先下架下子分类再下架该分类");
                }

            }

            articleClassify.setLastStageFlag(1);
            int successNum = articleClassifyDao.update(articleClassify);
            return successNum > 1 ? true : false;
        }catch (Exception e){
            logger.error("下架文章分类操作失败", e);
        }
        return false;
    }
}

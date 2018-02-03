package com.yryz.writer.modules.articleclassify.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.articleclassify.constant.ArticleClassifyConstant;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        if(articleClassifyDto.isPageFlag()){
            PageUtils.startPage(articleClassifyDto.getCurrentPage(), articleClassifyDto.getPageSize());
        }
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
                //查询分类下的文章个数
                long articleCount = articleClassifyDao.countArticleByClassifyId(articleClassify.getKid());
                articleClassifyVo.setArticleAmount(articleCount);
                Date createDate = articleClassify.getCreateDate();
                articleClassifyVo.setCreateDate(createDate == null ? "" : DATETIME_PATTERN.format(createDate));
                articleClassifyVoList.add(articleClassifyVo);
            }
        }
        return new PageModel<ArticleClassifyVo>().getPageList(list, articleClassifyVoList);
    }


    public ArticleClassifyVo detail(Long articleClassifyId) {
        ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class,articleClassifyId);
        ArticleClassify parentArticleClassify = null;
        Long parentId = articleClassify.getParentId();
        ArticleClassifyVo articleClassifyVo = new ArticleClassifyVo();
        if (articleClassifyVo != null) {
            //ArticleClassify to ArticleClassifyVo
            BeanUtils.copyProperties(articleClassify, articleClassifyVo);
        }
        //设置父类别属性
        if (parentId != null && parentId > 0) {
            parentArticleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, parentId);
            articleClassifyVo.setParentClassifyName(parentArticleClassify.getClassifyName());
        } else {
            articleClassifyVo.setParentId(0L);
            articleClassifyVo.setParentClassifyName("全部分类");
        }
        return articleClassifyVo;
    }

    @Override
    @Transactional
    public Boolean insert(ArticleClassify articleClassify) {
        try {
            Assert.notNull(articleClassify.getParentId(), "父类id不能为空");
            Assert.notNull(articleClassify.getLastUpdateUserId(), "修改人id不能为空");
            Assert.hasText(articleClassify.getClassifyName(), "分类名称不能为空");
            Assert.hasText(articleClassify.getClassifyDesc(), "分类描述不能为空");
            changeToNoFloor(articleClassify.getParentId(), articleClassify.getLastUpdateUserId());
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

    /**
     * 将父分类是末级分类的修改为非末级分类
     * @param parentId
     * @param lastUpdateUserId
     */
    private void changeToNoFloor(Long parentId, String lastUpdateUserId){
        //查父级分类
        ArticleClassify parent = articleClassifyDao.selectByKid(ArticleClassify.class, parentId);
        //父级分类是末级分类时
        if (null != parent && ArticleClassifyConstant.LAST_STAGE_YES == parent.getLastStageFlag()) {
            //父级分类上关联了文章
            if (articleClassifyDao.countArticleByClassifyId(parent.getKid()) > 0) {
                throw new IllegalArgumentException("父级分类下有文章，请移除文章再添加该分类");
            }
            //父级分类为末级分类，需要修改为非末级分类
            ArticleClassify updateClassify = new ArticleClassify();
            updateClassify.setId(parent.getId());
            updateClassify.setLastStageFlag(ArticleClassifyConstant.LAST_STAGE_NO);
            updateClassify.setLastUpdateUserId(lastUpdateUserId);
            articleClassifyDao.update(updateClassify);
        }
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

    @Transactional
    public Boolean shelveOn(Long articleClassifyId){
        try {
            Assert.notNull(articleClassifyId, "分类id不能为空");
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassifyId);
            if (null == articleClassify) {
                throw new IllegalArgumentException("文章分类不存在");
            }

            if (ArticleClassifyConstant.LAST_STAGE_YES == articleClassify.getLastStageFlag()) {
                ArticleClassify parentClassify = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassify.getParentId());
                //父级分类为下架，不能下架该分类
                if(parentClassify !=null && parentClassify.getShelveFlag()==1){
                    throw new IllegalArgumentException("该分类的父级分类为下架，请先上架父级分类再上架该分类");
                }
            }
            //上架
            articleClassify.setShelveFlag(0);
            int successNum = articleClassifyDao.update(articleClassify);
            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("上架文章分类操作失败", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean shelveOff(Long articleClassifyId) {
        try {
            Assert.notNull(articleClassifyId, "分类id不能为空");
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class,articleClassifyId);
            if (null == articleClassify) {
                throw new IllegalArgumentException("文章分类不存在");
            }
            //非末级分类
            if (ArticleClassifyConstant.LAST_STAGE_YES != articleClassify.getLastStageFlag()) {
                //该分类下子分类存在上架的，不能直接下架
                int count = articleClassifyDao.selectShelveOnChildCount(articleClassifyId);
                if (count > 0) {
                    throw new IllegalArgumentException("该分类下存在上架子分类，请先下架下子分类再下架该分类");
                }
            }else{//末级分类
                //该分类上关联了文章
                if (articleClassifyDao.countArticleByClassifyId(articleClassify.getKid()) > 0) {
                    throw new IllegalArgumentException("分类下有文章，请移除文章再下架该分类");
                }
            }
            articleClassify.setShelveFlag(1);
            int successNum = articleClassifyDao.update(articleClassify);
            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("下架文章分类操作失败", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean deleteArticleClassify(Long kid, String lastUpdateUserId) {
        try {
            Assert.notNull(kid, "分类id不能为空");
            Assert.notNull(lastUpdateUserId, "修改人id不能为空");
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, kid);
            if (null == articleClassify) {
                throw new IllegalArgumentException("文章分类不存在");
            }
            //非末级分类
            if (ArticleClassifyConstant.LAST_STAGE_YES != articleClassify.getLastStageFlag()) {
                throw new IllegalArgumentException("该分类下有子分类，请移除分类下子分类再删除");
            }else{//末级分类
                //该分类上关联了文章
                if (articleClassifyDao.countArticleByClassifyId(articleClassify.getKid()) > 0) {
                    throw new IllegalArgumentException("分类下有文章，请移除文章再删除该分类");
                }
            }
            int successNum = articleClassifyDao.deleteArticleClassify(kid, lastUpdateUserId);
            //判断该分类的父级分类是否存在其他子分类
            int count = articleClassifyDao.selectShelveOnChildCount(articleClassify.getParentId());
            if (count == 0) {
                //修改为末级分类
                ArticleClassify updateClassify = new ArticleClassify();
                updateClassify.setId(articleClassify.getParentId());
                updateClassify.setLastStageFlag(ArticleClassifyConstant.LAST_STAGE_YES);
                updateClassify.setLastUpdateUserId(lastUpdateUserId);
                articleClassifyDao.update(updateClassify);
            }
            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("删除文章分类操作失败", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean recoverArticleClassify(Long kid, String lastUpdateUserId) {
        try {
            Assert.notNull(kid, "分类id不能为空");
            Assert.notNull(lastUpdateUserId, "修改人id不能为空");
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, kid);
            if (null == articleClassify) {
                throw new IllegalArgumentException("文章分类不存在");
            }
            ArticleClassify parent = articleClassifyDao.selectByKid(ArticleClassify.class, articleClassify.getParentId());
            //末级分类
            if (null != parent && ArticleClassifyConstant.LAST_STAGE_YES == parent.getLastStageFlag()) {
                //父级分类上关联了文章
                if (articleClassifyDao.countArticleByClassifyId(articleClassify.getParentId()) > 0) {
                    throw new IllegalArgumentException("父级分类下有文章，请移除文章再恢复该分类");
                }
                //父级分类为末级分类，需要修改为非末级分类
                ArticleClassify updateClassify = new ArticleClassify();
                updateClassify.setId(parent.getId());
                updateClassify.setLastStageFlag(ArticleClassifyConstant.LAST_STAGE_NO);
                updateClassify.setLastUpdateUserId(lastUpdateUserId);
                articleClassifyDao.update(updateClassify);
            }
            int successNum =  articleClassifyDao.recoverClassify(kid, lastUpdateUserId);

            return successNum > 0 ? true : false;
        }catch (Exception e){
            logger.error("删除文章分类操作失败", e);
            throw e;
        }
    }

    @Override
    public Boolean checkArticleClassify(Long kid) {
        try {
            Assert.notNull(kid, "分类id不能为空");
            ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class, kid);
            //该分类是末级分类
            if (null != articleClassify && ArticleClassifyConstant.LAST_STAGE_YES == articleClassify.getLastStageFlag()) {
                //该末级分类上关联着文章
                int count = articleClassifyDao.countArticleByClassifyId(kid);
                return count > 0 ? false : true;
            }
        }catch (Exception e){
            logger.error("检查文章分类操作失败", e);
            throw e;
        }
        return true;
    }
}

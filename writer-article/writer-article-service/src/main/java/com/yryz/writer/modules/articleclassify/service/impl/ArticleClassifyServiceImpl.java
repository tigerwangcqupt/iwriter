package com.yryz.writer.modules.articleclassify.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.articleclassify.dao.persistence.ArticleClassifyDao;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.service.ArticleClassifyService;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                Date createDate = articleClassify.getCreateDate();
                articleClassifyVo.setCreateDate(createDate == null ? "" : DATETIME_PATTERN.format(articleClassify.getCreateDate()));
                articleClassifyVoList.add(articleClassifyVo);
            }
        }
        return new PageModel<ArticleClassifyVo>().getPageList(articleClassifyVoList);
    }


    public ArticleClassifyVo detail(Long articleClassifyId) {
        ArticleClassify articleClassify = articleClassifyDao.selectByKid(ArticleClassify.class,articleClassifyId);
        ArticleClassifyVo articleClassifyVo = new ArticleClassifyVo();
        if (articleClassifyVo != null) {
            //ArticleClassify to ArticleClassifyVo
        }
        return articleClassifyVo;
    }

    @Override
    public Boolean insert(ArticleClassify articleClassify) {
        try {
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

    @Override
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
}

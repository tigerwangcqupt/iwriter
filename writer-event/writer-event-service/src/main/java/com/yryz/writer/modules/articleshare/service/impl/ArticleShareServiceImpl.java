package com.yryz.writer.modules.articleshare.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.dao.persistence.ArticleShareDao;
import com.yryz.writer.modules.articleshare.service.ArticleShareService;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleShareServiceImpl extends BaseServiceImpl implements ArticleShareService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleShareServiceImpl.class);

    @Autowired
    private ArticleShareDao articleShareDao;

    protected BaseDao getDao() {
        return articleShareDao;
    }

    public PageList<ArticleShareVo> selectList(ArticleShareDto articleShareDto){
        PageUtils.startPage(articleShareDto.getCurrentPage(), articleShareDto.getPageSize());
        List<ArticleShare> list = articleShareDao.selectList(articleShareDto);
        List<ArticleShareVo> articleShareVoList = new ArrayList <ArticleShareVo>();
        if(list != null && list.size() > 0) {
            for(ArticleShare articleShare : list){
                ArticleShareVo articleShareVo = new ArticleShareVo();
                //ArticleShare to ArticleShareVo
                articleShareVoList.add(articleShareVo);
            }
        }
        return new PageModel<ArticleShareVo>().getPageList(articleShareVoList);
    }


    public ArticleShareVo detail(Long articleShareId) {
        ArticleShare articleShare = articleShareDao.selectByKid(ArticleShare.class,articleShareId);
        ArticleShareVo articleShareVo = new ArticleShareVo();
        if (articleShareVo != null) {
            //ArticleShare to ArticleShareVo
        }
        return articleShareVo;
    }
 }

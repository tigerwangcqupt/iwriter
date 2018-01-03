package com.yryz.writer.modules.draft.service.impl;

import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.utils.PageUtils;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.dao.persistence.DraftDao;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.service.DraftService;
import com.yryz.writer.modules.draft.vo.DraftVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class DraftServiceImpl extends BaseServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    protected BaseDao getDao() {
        return draftDao;
    }

    public PageList<DraftVo> selectList(DraftDto draftDto) {
        PageUtils.startPage(draftDto.getCurrentPage(), draftDto.getPageSize());

        //获取状态,已发表需查询文章表,其余查询稿件表
        //列表参数:0 全部,1 已发表,2 未通过,3 草稿
        Integer status = draftDto.getStatus();
        switch (status) {
            case 0:
                draftDto.setDraftStatus(null);
                break;
            case 1:
                draftDto.setDraftStatus(3);
                break;
            case 2:
                draftDto.setDraftStatus(2);
                break;
            case 3:
                draftDto.setDraftStatus(0);
                break;
            default:
                draftDto.setDraftStatus(null);
                break;
        }

        //查询稿件表
        List<Draft> list = draftDao.selectList(draftDto);
        List<DraftVo> draftVoList = new ArrayList<DraftVo>();
        if (list != null && list.size() > 0) {
            for (Draft draft : list) {
                DraftVo draftVo = new DraftVo();
                //Draft to DraftVo
                draftVo.setId(draft.getId());
                draftVo.setTitle(draft.getTitle());
                draftVo.setContentHtml(draft.getContentHtml());
                draftVo.setCoverImgUrl(draft.getCoverImgUrl());
                draftVo.setDescription(draft.getDescription());
                draftVo.setDraftFee(draft.getDraftFee());
                draftVo.setDraftStatus(draft.getDraftStatus());
                draftVo.setReason(draft.getReason());
                draftVo.setSuggest(draft.getSuggest());
                draftVo.setVideoUrl(draft.getVideoUrl());
                draftVoList.add(draftVo);
            }
        }
        return new PageModel<DraftVo>().getPageList(draftVoList);
    }


    public DraftVo detail(Long draftId) {
        Draft draft = draftDao.selectByKid(Draft.class, draftId);
        DraftVo draftVo = new DraftVo();
        if (draftVo != null) {
            //Draft to DraftVo
        }
        return draftVo;
    }

    @Override
    public int add(Draft draft) {
        draft.setKid(1L);
        Long appId = draft.getAppId();
        return draftDao.insertByPrimaryKeySelective(draft);
    }
}

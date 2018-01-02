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

import java.util.ArrayList;
import java.util.List;


@Service
public class DraftServiceImpl extends BaseServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    protected BaseDao getDao() {
        return draftDao;
    }

    public PageList<DraftVo> selectList(DraftDto draftDto){
        PageUtils.startPage(draftDto.getCurrentPage(), draftDto.getPageSize());
        List<Draft> list = draftDao.selectList(draftDto);
        List<DraftVo> draftVoList = new ArrayList <DraftVo>();
        if(list != null && list.size() > 0) {
            for(Draft draft : list){
                DraftVo draftVo = new DraftVo();
                //Draft to DraftVo
                draftVoList.add(draftVo);
            }
        }
        return new PageModel<DraftVo>().getPageList(draftVoList);
    }


    public DraftVo detail(Long draftId) {
        Draft draft = draftDao.selectByKid(Draft.class,draftId);
        DraftVo draftVo = new DraftVo();
        if (draftVo != null) {
            //Draft to DraftVo
        }
        return draftVo;
    }
 }

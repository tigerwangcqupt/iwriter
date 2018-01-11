package com.yryz.writer.modules.draft.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.task.vo.AppVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * @author luohao
 * @ClassName: DraftService
 * @Description: Draft业务访问接口
 * @date 2017-12-29 14:40:13
 */
@Repository
public interface DraftService extends BaseService {

    PageList<DraftVo> selectList(DraftDto draftDto);

    DraftVo detail(Long draftId);

    Long add(Draft draft);

    List<AppVo> selectAppByAppliName(String appliName);
}
package com.yryz.writer.modules.draft.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.vo.DraftVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: DraftService
  * @Description: Draft业务访问接口
  * @author luohao
  * @date 2017-12-29 14:40:13
  *
 */
@Repository
public interface DraftService extends BaseService {

   PageList<DraftVo> selectList(DraftDto draftDto);

   DraftVo detail(Long draftId);

}
package com.yryz.writer.modules.draft.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.draft.DraftApi;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.service.DraftService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DraftProvider implements DraftApi {

	private static final Logger logger = LoggerFactory.getLogger(DraftProvider.class);

	@Autowired
	private DraftService draftService;

	/**
	*  获取Draft明细
	*  @param  draftId
	*  @return
	* */
	public RpcResponse<Draft> get(Long draftId) {
		try {
			return ResponseModel.returnObjectSuccess(draftService.get(Draft.class, draftId));
		} catch (Exception e) {
			logger.error("获取Draft明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Draft明细
	*  @param  draftId
	*  @return
	* */
	public RpcResponse<DraftVo> detail(Long draftId) {
		try {
			return ResponseModel.returnObjectSuccess(draftService.detail(draftId));
		} catch (Exception e) {
			logger.error("获取Draft明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取Draft列表
    * @param draftDto
    * @return
    *
	*/
    public RpcResponse<PageList<DraftVo>> list(DraftDto draftDto) {
        try {
			 return ResponseModel.returnListSuccess(draftService.selectList(draftDto));
        } catch (Exception e) {
        	logger.error("获取Draft列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

}

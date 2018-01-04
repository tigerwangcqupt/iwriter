package com.yryz.writer.modules.writer.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.writer.WriterAuditApi;
import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.service.WriterAuditService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterAuditProvider implements WriterAuditApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterAuditProvider.class);

	@Autowired
	private WriterAuditService writerAuditService;

	/**
	*  获取WriterAudit明细
	*  @param  writerAuditId
	*  @return
	* */
	public RpcResponse<WriterAudit> get(Long writerAuditId) {
		try {
			return ResponseModel.returnObjectSuccess(writerAuditService.get(WriterAudit.class, writerAuditId));
		} catch (Exception e) {
			logger.error("获取WriterAudit明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取WriterAudit明细
	*  @param  writerAuditId
	*  @return
	* */
	public RpcResponse<WriterAuditVo> detail(Long writerAuditId) {
		try {
			return ResponseModel.returnObjectSuccess(writerAuditService.detail(writerAuditId));
		} catch (Exception e) {
			logger.error("获取WriterAudit明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取WriterAudit列表
    * @param writerAuditDto
    * @return
    *
	*/
    public RpcResponse<PageList<WriterAuditVo>> list(WriterAuditDto writerAuditDto) {
        try {
			 return ResponseModel.returnListSuccess(writerAuditService.selectList(writerAuditDto));
        } catch (Exception e) {
        	logger.error("获取WriterAudit列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

}
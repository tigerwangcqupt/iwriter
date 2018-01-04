package com.yryz.writer.modules.writer.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.vo.WriterVo;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import com.yryz.writer.modules.writer.service.WriterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterProvider implements WriterApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterProvider.class);

	@Autowired
	private WriterService writerService;
	
	@Autowired
	private IdAPI idAPI;
	
	@Autowired
	private WriterAuditService writerAuditService;

	/**
	*  获取Writer明细
	*  @param  writerId
	*  @return
	* */
	public RpcResponse<Writer> get(Long writerId) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.get(Writer.class, writerId));
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Writer明细
	*  @param  writerId
	*  @return
	* */
	public RpcResponse<WriterVo> detail(Long writerId) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.detail(writerId));
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取Writer列表
    * @param writerDto
    * @return
    *
	*/
    public RpcResponse<PageList<WriterVo>> list(WriterDto writerDto) {
        try {
			 return ResponseModel.returnListSuccess(writerService.selectList(writerDto));
        } catch (Exception e) {
        	logger.error("获取Writer列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<WriterVo> updateWriter(Writer writer) {
		 try {
			 writerService.update(writer);
			 WriterVo writerVo = writerService.detail(writer.getKid());
			 return ResponseModel.returnObjectSuccess(writerVo);
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}

	@Override
	@Transactional
	public RpcResponse<WriterVo> submitAudit(Writer writer) {
		 try {
			 //更新写手信息
			 writerService.update(writer);
			 //新增审核信息
			 WriterAudit writerAudit = new WriterAudit();
			 Long kid  = idAPI.getId("yryz_writer_audit_history");
			 writerAudit.setKid(kid);
			 writerAudit.setWriterKid(writer.getKid());
			 writerAudit.setUserName(writer.getUserName());
			 writerAudit.setIdentityCard(writer.getIdentityCard());
			 writerAudit.setIdentityCardPhoto(writer.getIdentityCardPhoto());
			 writerAudit.setProvice(writer.getProvice());
			 writerAudit.setCity(writer.getCity());
			 writerAudit.setTel(writer.getTel());
			 writerAudit.setEmail(writer.getEmail());
			 writerAudit.setAuditStatus(1);
			 writerAudit.setCreateUserId(writer.getKid().toString());
			 writerAudit.setLastUpdateUserId(writer.getKid().toString());
			 writerAuditService.insertByPrimaryKeySelective(writerAudit);
			 WriterVo writerVo = writerService.detail(writer.getKid());
			 return ResponseModel.returnObjectSuccess(writerVo);
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}

}

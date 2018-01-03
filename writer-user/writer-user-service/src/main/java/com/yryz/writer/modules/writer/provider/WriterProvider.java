package com.yryz.writer.modules.writer.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.vo.WriterVo;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.service.WriterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterProvider implements WriterApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterProvider.class);

	@Autowired
	private WriterService writerService;

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
			 return ResponseModel.returnObjectSuccess(null);
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}

}

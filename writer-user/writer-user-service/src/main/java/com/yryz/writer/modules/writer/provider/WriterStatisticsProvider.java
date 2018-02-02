package com.yryz.writer.modules.writer.provider;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.writer.WriterStatisticsApi;
import com.yryz.writer.modules.writer.vo.WriterStatisticsVo;
import com.yryz.writer.modules.writer.dto.WriterStatisticsDto;
import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.modules.writer.service.WriterStatisticsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterStatisticsProvider implements WriterStatisticsApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterStatisticsProvider.class);

	@Autowired
	private WriterStatisticsService writerStatisticsService;

	/**
	*  获取WriterStatistics明细
	*  @param  writerStatisticsId
	*  @return
	* */
	public RpcResponse<WriterStatistics> get(Long kid) {
		try {
			return ResponseModel.returnObjectSuccess(writerStatisticsService.get(WriterStatistics.class, kid));
		} catch (Exception e) {
			logger.error("获取WriterStatistics明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取WriterStatistics明细
	*  @param  writerStatisticsId
	*  @return
	* */
	public RpcResponse<WriterStatisticsVo> detail(Long kid) {
		try {
			return ResponseModel.returnObjectSuccess(writerStatisticsService.detail(kid));
		} catch (Exception e) {
			logger.error("获取WriterStatistics明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取WriterStatistics列表
    * @param writerStatisticsDto
    * @return
    *
	*/
    public RpcResponse<PageList<WriterStatisticsVo>> list(WriterStatisticsDto writerStatisticsDto) {
        try {
			 return ResponseModel.returnListSuccess(writerStatisticsService.selectList(writerStatisticsDto));
        } catch (Exception e) {
        	logger.error("获取WriterStatistics列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<WriterStatistics> insert(WriterStatistics writerStatistics) {
		try {
			return ResponseModel.returnObjectSuccess(writerStatisticsService.insertWriterStatistics(writerStatistics));
		} catch (Exception e) {
			logger.error("新增WriterStatistics失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<WriterStatistics> update(WriterStatistics writerStatistics) {
		try {
			return ResponseModel.returnObjectSuccess(writerStatisticsService.updateWriterStatistics(writerStatistics));
		} catch (Exception e) {
			logger.error("更新WriterStatistics失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

package com.yryz.writer.modules.writer.provider;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageVo;
import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;
import com.yryz.writer.modules.writer.WriterAuditApi;
import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.service.WriterAuditService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterAuditProvider implements WriterAuditApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterAuditProvider.class);

	@Autowired
	private WriterAuditService writerAuditService;
	
	@Autowired
	private ProvinceApi provinceApi;
	
	@Autowired
	private CityApi cityApi;
	

	/**
	*  获取WriterAudit明细
	*  @param  writerAuditId
	*  @return
	* */
	public RpcResponse<WriterAudit> get(Long kid) {
		try {
			return ResponseModel.returnObjectSuccess(writerAuditService.get(WriterAudit.class, kid));
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
	public RpcResponse<WriterAuditVo> detail(Long kid) {
		try {
			WriterAuditVo writerAuditVo = writerAuditService.detail(kid);
			if(writerAuditVo!=null){
				ProvinceVo provinceVo = provinceApi.selectProvinces(writerAuditVo.getProvice()).getData();
				if(provinceVo!=null){
					writerAuditVo.setProviceName(provinceVo.getProvinceName());
				}
				CityVo cityVo = cityApi.selectCity(writerAuditVo.getCity()).getData();
				if(cityVo!=null){
					writerAuditVo.setCityName(cityVo.getCityName());
				}
			}
			return ResponseModel.returnObjectSuccess(writerAuditVo);
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
    
    public RpcResponse<List<WriterAuditVo>> exportList(WriterAuditDto writerAuditDto) {
        try {
			 return ResponseModel.returnListSuccess(writerAuditService.exportList(writerAuditDto));
        } catch (Exception e) {
        	 logger.error("获取WriterAudit列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<Integer> audit(WriterAuditVo writerAuditVo) {
		 try {
			 return ResponseModel.returnObjectSuccess(writerAuditService.audit(writerAuditVo));
        } catch (Exception e) {
        	 logger.error("更新WriterAudit状态失败", e);
       		 return ResponseModel.returnException(e);
        }
	}

}

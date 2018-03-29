package com.yryz.writer.modules.indexurl.provider;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.common.web.ResponseModel;


import com.yryz.writer.modules.indexurl.IndexUrlConfigApi;
import com.yryz.writer.modules.indexurl.entity.IndexUrlConfig;
import com.yryz.writer.modules.indexurl.vo.IndexUrlConfigVo;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;
import com.yryz.writer.modules.indexurl.service.IndexUrlConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexUrlConfigProvider implements IndexUrlConfigApi {

	private static final Logger logger = LoggerFactory.getLogger(IndexUrlConfigProvider.class);

	@Autowired
	private IndexUrlConfigService indexUrlConfigService;

	/**
	*  获取IndexUrlConfig明细
	*  @param  indexUrlConfigId
	*  @return
	* */
	public RpcResponse<IndexUrlConfig> get(Long indexUrlConfigId) {
		try {
			return ResponseModel.returnObjectSuccess(indexUrlConfigService.get(IndexUrlConfig.class, indexUrlConfigId));
		} catch (Exception e) {
			logger.error("获取IndexUrlConfig明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取IndexUrlConfig明细
	*  @param  indexUrlConfigId
	*  @return
	* */
	public RpcResponse<IndexUrlConfigVo> detail(Long indexUrlConfigId) {
		try {
			return ResponseModel.returnObjectSuccess(indexUrlConfigService.detail(indexUrlConfigId));
		} catch (Exception e) {
			logger.error("获取IndexUrlConfig明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取IndexUrlConfig列表
    * @param indexUrlConfigDto
    * @return
    *
	*/
    public RpcResponse<PageList<IndexUrlConfigVo>> list(IndexUrlConfigDto indexUrlConfigDto) {
        try {
			 return ResponseModel.returnListSuccess(indexUrlConfigService.selectList(indexUrlConfigDto));
        } catch (Exception e) {
        	logger.error("获取IndexUrlConfig列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<PageList<IndexUrlConfigVo>> adminList(IndexUrlConfigDto indexUrlConfigDto) {
		try {
			return ResponseModel.returnListSuccess(indexUrlConfigService.selectAdminList(indexUrlConfigDto));
		} catch (Exception e) {
			logger.error("获取IndexUrlConfig列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<IndexUrlConfig> saveIndexConfig(IndexUrlConfig indexUrlConfig) {
		try {
			return ResponseModel.returnListSuccess(indexUrlConfigService.saveIndexConfig(indexUrlConfig));
		} catch (Exception e) {
			logger.error("保存url配置失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<IndexUrlConfig> updateIndexConfig(IndexUrlConfig indexUrlConfig) {
		try {
			return ResponseModel.returnListSuccess(indexUrlConfigService.updateIndexConfig(indexUrlConfig));
		} catch (Exception e) {
			logger.error("保存url配置失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

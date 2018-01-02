package com.yryz.writer.modules.indexcolumn.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.service.IndexColumnService;
import com.yryz.writer.modules.indexcolumn.entity.IndexColumn;
import com.yryz.writer.modules.indexcolumn.IndexColumnApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页栏目服务提供者
 */
@Service
public class IndexColumnProvider implements IndexColumnApi {

	private static final Logger logger = LoggerFactory.getLogger(IndexColumnProvider.class);

	@Autowired
	private IndexColumnService indexColumnService;

	/**
	*  获取IndexColumn明细
	*  @param  indexColumnId
	*  @return
	* */
	public RpcResponse<IndexColumn> get(Long indexColumnId) {
		try {
			return ResponseModel.returnObjectSuccess(indexColumnService.get(IndexColumn.class, indexColumnId));
		} catch (Exception e) {
			logger.error("获取IndexColumn明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取IndexColumn明细
	*  @param  indexColumnId
	*  @return
	* */
	public RpcResponse<IndexColumnVo> detail(Long indexColumnId) {
		try {
			return ResponseModel.returnObjectSuccess(indexColumnService.detail(indexColumnId));
		} catch (Exception e) {
			logger.error("获取IndexColumn明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取IndexColumn列表
    * @param indexColumnDto
    * @return
    *
	*/
    public RpcResponse<PageList<IndexColumnVo>> list(IndexColumnDto indexColumnDto) {
        try {
			 return ResponseModel.returnListSuccess(indexColumnService.selectList(indexColumnDto));
        } catch (Exception e) {
        	logger.error("获取IndexColumn列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<List<IndexColumnVo>> list() {
		try {
			return ResponseModel.returnListSuccess(indexColumnService.selectAll());
		} catch (Exception e) {
			logger.error("获取IndexColumn列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

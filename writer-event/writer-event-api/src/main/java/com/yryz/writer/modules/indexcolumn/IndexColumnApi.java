package com.yryz.writer.modules.indexcolumn;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.entity.IndexColumn;

/**
 * 
 * @ClassName: IndexColumnApi
 * @Description: IndexColumnApi接口
 * @author huyangyang
 * @date 2018-01-02 10:04:46
 *
 */
public interface IndexColumnApi {

	/**
	*  获取IndexColumn明细
	*  @param  id
	*  @return
	* */
	RpcResponse<IndexColumn> get(Long id);

    /**
    *  获取IndexColumn明细
    *  @param  id
    *  @return
    * */
    RpcResponse<IndexColumnVo> detail(Long id);

    /**
    * 获取IndexColumn列表
    * @param indexColumnDto
    * @return
    * */
    RpcResponse<PageList<IndexColumnVo>> list(IndexColumnDto indexColumnDto);

}

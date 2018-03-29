package com.yryz.writer.modules.indexurl;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;
import com.yryz.writer.modules.indexurl.entity.IndexUrlConfig;
import com.yryz.writer.modules.indexurl.vo.IndexUrlConfigVo;

/**
 * 
 * @ClassName: IndexUrlConfigApi
 * @Description: IndexUrlConfigApi接口
 * @author wangsenyong
 * @date 2018-03-29 15:11:09
 *
 */
public interface IndexUrlConfigApi {

	/**
	*  获取IndexUrlConfig明细
	*  @param  id
	*  @return
	* */
	RpcResponse<IndexUrlConfig> get(Long id);

    /**
    *  获取IndexUrlConfig明细
    *  @param  id
    *  @return
    * */
    RpcResponse<IndexUrlConfigVo> detail(Long id);

    /**
    * 获取IndexUrlConfig列表
    * @param indexUrlConfigDto
    * @return
    * */
    RpcResponse<PageList<IndexUrlConfigVo>> list(IndexUrlConfigDto indexUrlConfigDto);

	/**
	 * 获取IndexUrlConfig列表
	 * @param indexUrlConfigDto
	 * @return
	 * */
	RpcResponse<PageList<IndexUrlConfigVo>> adminList(IndexUrlConfigDto indexUrlConfigDto);


	/**
	 *  保存IndexConfig
	 *  @param  indexUrlConfig
	 *  @return
	 * */
	RpcResponse<IndexUrlConfig> saveIndexConfig(IndexUrlConfig indexUrlConfig);

	/**
	 *  修改IndexConfig
	 *  @param  indexUrlConfig
	 *  @return
	 * */
	RpcResponse<IndexUrlConfig> updateIndexConfig(IndexUrlConfig indexUrlConfig);
}

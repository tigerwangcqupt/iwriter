package com.yryz.writer.modules.indexcolumn;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.entity.IndexColumn;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.message.vo.IndexTipsVo;

import java.util.List;

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

	/**
	 * 获取首页栏目列表（未删除）
	 * @return
	 * */
	RpcResponse<List<IndexColumnVo>> listByWriter(IndexColumnDto indexColumnDto);

	/**
	 * 获得写手的消息栏目（包含每个栏目的消息数）
	 * @param indexColumnDto
	 * @return
	 */
	public RpcResponse<List<IndexTipsVo>> getIndexTips(IndexColumnDto indexColumnDto);
}

package com.yryz.writer.modules.writer;

import java.util.List;
import java.util.Map;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterAdminVo;
import com.yryz.writer.modules.writer.vo.WriterVo;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;

/**
 * 
 * @ClassName: WriterApi
 * @Description: WriterApi接口
 * @author liuyanjun
 * @date 2018-01-03 15:03:10
 *
 */
public interface WriterApi {

	/**
	 * 用户注册
	 */
	RpcResponse<Integer> register(Writer user);

	/**
	 * 用户修改
	 * @param user
	 * @return
	 */
	RpcResponse<Integer> updateByPrimaryKeySelective(Writer user);

	/**
	*  获取Writer明细
	*  @param  id
	*  @return
	* */
	RpcResponse<Writer> get(Long id);


	/**
	 *  获取Writer明细
	 *  @param  phone
	 *  @return
	 * */
	RpcResponse<Writer> selectByPhone(String phone);

	/**
	 *  获取用户token
	 *  @param  custId
	 *  @return
	 * */
	RpcResponse<String> getUserToken(String custId);


    /**
    *  获取Writer明细
    *  @param  id
    *  @return
    * */
    RpcResponse<WriterVo> detail(Long id);

    /**
    * 获取Writer列表
    * @param writerDto
    * @return
    * */
    RpcResponse<PageList<WriterVo>> list(WriterDto writerDto);
    
    /**
	 * 
	 *
	 * @param writer
	 * @return
	 */
	RpcResponse<WriterVo> updateWriter(Writer writer);
	
	RpcResponse<WriterVo> submitAudit(Writer writer);


	/**
	 * 获取Writer列表
	 * @param writerDto
	 * @return
	 * */
	RpcResponse<PageList<WriterAdminVo>> listAdmin(WriterDto writerDto);

	/**
	 * 后台查询写手收益列表
	 * @param writerDto
	 * @return
	 */
	RpcResponse<PageList<WriterAdminRefProfit>> selectAdminProfitList(WriterDto writerDto);

	/**
	 * 后台查询写手收益列表
	 * @param writerDto
	 * @return
	 */
	List<WriterAdminRefProfit> selectAllAdminProfitList(WriterDto writerDto);
}

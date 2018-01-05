package com.yryz.writer.modules.fans;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.fans.vo.FansVo;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.entity.Fans;

/**
 * 
 * @ClassName: FansApi
 * @Description: FansApi接口
 * @author luohao
 * @date 2018-01-02 20:08:19
 *
 */
public interface FansApi {

	/**
	*  获取Fans明细
	*  @param  id
	*  @return
	* */
	RpcResponse<Fans> get(Long id);

    /**
    *  获取Fans明细
    *  @param  id
    *  @return
    * */
    RpcResponse<FansVo> detail(Long id);

    /**
    * 获取Fans列表
    * @param fansDto
    * @return
    * */
    RpcResponse<PageList<FansVo>> list(FansDto fansDto);

    /**
    * 获取新增Fans列表
    * @param fansDto
    * @return
    * */
    RpcResponse<PageList<FansVo>> NewFansList(FansDto fansDto);

    //获取粉丝数量
    RpcResponse<Integer> selectCount(Long userId);

    //获取新增粉丝数量
    RpcResponse<Integer> selectNewFansCount(Long userId);
}

package com.yryz.writer.modules.ad.api;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.api
 * @Desc:
 * @Date: 2018/6/1.
 */
public interface AdApi {

    /**
     * 查询-带分页
     * @param adDto
     * @return
     */
    RpcResponse<PageList<Ad>> getList(AdDto adDto);

    /**
     * 新增
     * @param ad
     * @return
     */
    RpcResponse<Integer> insert(Ad ad);

    /**
     * 修改
     * @param ad
     * @return
     */
    RpcResponse<Integer> update(Ad ad);
}

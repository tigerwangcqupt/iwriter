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

    RpcResponse<PageList<Ad>> getList(AdDto adDto);
}

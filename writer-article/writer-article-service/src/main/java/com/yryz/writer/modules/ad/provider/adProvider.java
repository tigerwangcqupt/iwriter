package com.yryz.writer.modules.ad.provider;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.ad.api.AdApi;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;
import com.yryz.writer.modules.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.provider
 * @Desc:
 * @Date: 2018/6/1.
 */
@Service
public class adProvider implements AdApi {

    @Autowired
    private AdService adService;

    @Override
    public RpcResponse<PageList<Ad>> getList(AdDto adDto) {
        return ResponseModel.returnObjectSuccess(adService.getList(adDto));
    }

    @Override
    public RpcResponse<Integer> insert(Ad ad) {
        //设置新增广告的默认排序在最后
        Integer sort = adService.getMaxSort();
        ad.setSort(sort + 1);
        return ResponseModel.returnObjectSuccess(adService.insert(ad));
    }

    @Override
    public RpcResponse<Integer> update(Ad ad) {
        return ResponseModel.returnObjectSuccess(adService.update(ad));
    }
}

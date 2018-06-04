package com.yryz.writer.modules.ad.service;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;
import org.springframework.stereotype.Repository;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.service
 * @Desc:
 * @Date: 2018/6/1.
 */
@Repository
public interface AdService extends BaseService {

    PageList<Ad> getList(AdDto adDto);
}

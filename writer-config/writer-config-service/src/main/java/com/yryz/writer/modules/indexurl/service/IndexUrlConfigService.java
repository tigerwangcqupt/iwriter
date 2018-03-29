package com.yryz.writer.modules.indexurl.service;


import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;
import com.yryz.writer.modules.indexurl.entity.IndexUrlConfig;
import com.yryz.writer.modules.indexurl.vo.IndexUrlConfigVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: IndexUrlConfigService
  * @Description: IndexUrlConfig业务访问接口
  * @author wangsenyong
  * @date 2018-03-29 15:11:09
  *
 */
@Repository
public interface IndexUrlConfigService extends BaseService {

   PageList<IndexUrlConfigVo> selectList(IndexUrlConfigDto indexUrlConfigDto);

   PageList<IndexUrlConfigVo> selectAdminList(IndexUrlConfigDto indexUrlConfigDto);

   IndexUrlConfigVo detail(Long indexUrlConfigId);

   public IndexUrlConfig saveIndexConfig(IndexUrlConfig indexUrlConfig);

   public IndexUrlConfig updateIndexConfig(IndexUrlConfig indexUrlConfig);

}
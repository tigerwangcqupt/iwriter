package com.yryz.writer.modules.fans.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.vo.FansVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: FansService
  * @Description: Fans业务访问接口
  * @author luohao
  * @date 2018-01-02 20:08:19
  *
 */
@Repository
public interface FansService extends BaseService {

   PageList<FansVo> selectList(FansDto fansDto);

   FansVo detail(Long fansId);

}
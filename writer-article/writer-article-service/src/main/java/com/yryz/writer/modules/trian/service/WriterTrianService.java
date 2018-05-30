package com.yryz.writer.modules.trian.service;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.vo.WriterTrianVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.service
 * @Desc:
 * @Date: 2018/5/29.
 */
@Repository
public interface WriterTrianService extends BaseService{

    PageList<WriterTrianVo> getList(WriterTrianDto writerTrianDto);

    int getCount(WriterTrianDto writerTrianDto);
}

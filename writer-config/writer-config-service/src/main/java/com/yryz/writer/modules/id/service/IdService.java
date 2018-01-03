package com.yryz.writer.modules.id.service;


import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.entity.CodeModel;
import com.yryz.writer.modules.id.entity.CodeModelDto;
import com.yryz.writer.modules.id.entity.IDRequest;
import com.yryz.writer.modules.id.entity.IDResponse;


/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/14
 * @description
 */
public interface IdService {
    /**
     * 根据type获取id
     * @param type
     * @return
     */
    Long getId(String type);

    /**
     * 返回String类型Id
     * @return
     */
    String getId();

    IDResponse addType(IDRequest request);

    PageList<CodeModel> list(CodeModelDto dto);
}

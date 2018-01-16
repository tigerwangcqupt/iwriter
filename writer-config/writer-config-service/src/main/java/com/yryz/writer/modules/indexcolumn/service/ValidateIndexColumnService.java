package com.yryz.writer.modules.indexcolumn.service;

import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;

/**
 * File Name: ValidateIndexColumnService
 * Package Name: com.yryz.writer.modules.indexcolumn.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-16 14:48
 **/
public interface ValidateIndexColumnService {

    /**
     * 校验写手首页栏目参数和消息栏目参数
     * @param indexColumnDto
     * @return
     */
    boolean validateSelect(IndexColumnDto indexColumnDto);
}

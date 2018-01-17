package com.yryz.writer.modules.indexcolumn.service.impl;

import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.service.ValidateIndexColumnService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * File Name: ValidateIndexColumServiceImpl
 * Package Name: com.yryz.writer.modules.indexcolumn.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-16 14:50
 **/
@Service
public class ValidateIndexColumServiceImpl implements ValidateIndexColumnService {

    @Override
    public boolean validateSelect(IndexColumnDto indexColumnDto) {
        if (indexColumnDto == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        Assert.notNull(indexColumnDto.getCustId(), "写手id不能为空");
        return true;
    }
}

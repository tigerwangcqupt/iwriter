package com.yryz.writer.modules.id.web;

import com.alibaba.fastjson.JSON;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.QsourceException;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.api.CodeAPI;
import com.yryz.writer.modules.id.entity.CodeModel;
import com.yryz.writer.modules.id.entity.CodeModelDto;
import com.yryz.writer.modules.id.entity.IDRequest;
import com.yryz.writer.modules.id.entity.IDResponse;
import com.yryz.writer.modules.id.service.IdService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/9/18
 * @description
 */
@Service("codeAPI")
public class CodeAPIImpl implements CodeAPI {
    private static final Logger logger = LoggerFactory.getLogger(CodeAPIImpl.class);

    @Autowired
    private IdService idService;

    /**
     * 发号器配置列表
     *
     * @param dto
     * @return
     */
    @Override
    public PageList<CodeModel> list(CodeModelDto dto) throws QsourceException {
        return idService.list(dto);
    }

    /**
     * 增加业务类型，指定发号的宽度
     * eg: type="qshop_order", codeLength=8,
     * 给qshop_order发起始宽度为8的号码,后期发号递增
     *
     * @throws QsourceException
     */
    @Override
    public IDResponse addType(IDRequest request) throws QsourceException {
        logger.info("addType request:{}", JSON.toJSONString(request));
        if (request == null || StringUtils.isBlank(request.getType())
                || request.getCodeLength() == null || request.getCodeLength() <1) {
            logger.error("addType input paramsError");
            throw new QsourceException(ExceptionEnum.ValidateException.getCode(),
                    ExceptionEnum.ValidateException.getMsg(),
                    ExceptionEnum.ValidateException.getErrorMsg());
        }
        try {
            return idService.addType(request);
        } catch (Exception e) {
            logger.error("IDAPI addType error", e);
            throw new QsourceException(ExceptionEnum.SysException.getCode(),
                    ExceptionEnum.SysException.getMsg(),
                    ExceptionEnum.SysException.getErrorMsg());
        }
    }
}

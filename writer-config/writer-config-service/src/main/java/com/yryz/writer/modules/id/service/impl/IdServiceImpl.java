package com.yryz.writer.modules.id.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.distributed.lock.DistributedLockUtils;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.dao.persistence.IdDao;
import com.yryz.writer.modules.id.entity.CodeModel;
import com.yryz.writer.modules.id.entity.CodeModelDto;
import com.yryz.writer.modules.id.entity.IDRequest;
import com.yryz.writer.modules.id.entity.IDResponse;
import com.yryz.writer.modules.id.service.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/14
 * @description
 */
@Service
public class IdServiceImpl implements IdService {
    private static final Logger logger = LoggerFactory.getLogger(IdServiceImpl.class);

    private static final int DEFAULT_CODE_LENGTH = 5;
    private static final String ID_LOCK_NAME = "idGenerator";

    @Autowired
    private IdDao idDao;

//    @Autowired
//    private IdGenerator idGenerator;


    @Transactional
    @Override
    public Long getId(String type) {
        Long result = null;
        String lock = null;
        long start = System.currentTimeMillis();
        try {
            lock = DistributedLockUtils.lock(ID_LOCK_NAME, type);
            CodeModel codeModel = idDao.selectByType(type);
            if (codeModel != null) {
                // 已经配置，获取新的code
                long current = codeModel.getCurrent();
                current++;
                result = current;
                int flag = idDao.updateByType(type, current);
                //logger.info("updateByType from db:{}", flag);
            } else {
                logger.info("generate id for new type: {}", type);
                CodeModel model = buildNewCodeModel(type);
                result = model.getCurrent();
                int flag = idDao.insertCodeModel(model);
                //logger.info("insertCodeModel from db:{}", flag);
            }
        } catch (Exception e) {
            logger.error("getId error", e);
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "getId error for type: " + type);
        } finally {
            DistributedLockUtils.unlock(ID_LOCK_NAME, lock);
        }
        logger.info("getId cost time: {}", System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 如果type为未指定类型，则默认发号范围为5位
     * @param type
     * @return
     */
    private CodeModel buildNewCodeModel(String type) {
        CodeModel model = new CodeModel();
        model.setType(type);
        model.setCodeLength(DEFAULT_CODE_LENGTH);
        model.setCurrent(getSetInitial(DEFAULT_CODE_LENGTH));
        Date now = new Date();
        model.setCreateDate(now);
        model.setLastUpdateDate(now);
        return model;
    }

    private Long getInitial() {
        return (long)(Math.random() * 10000 + 10000);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public IDResponse addType(IDRequest request) {
        IDResponse response = new IDResponse();

        String type = request.getType();
        Integer length = request.getCodeLength();
        CodeModel model = idDao.selectByType(type);
        if (model != null) {
            response.setMsg("该业务类型已存在");
            return response;
        }
        CodeModel codeModel = buildInitialCodeModel(type, length);
        logger.info("addType codeModel: {}", JSON.toJSONString(codeModel));
        int result = idDao.insertCodeModel(codeModel);
        response.setFlag(result == 1);
        return response;
    }

    @Override
    public PageList<CodeModel> list(CodeModelDto codeModelDto) {
        PageHelper.startPage(codeModelDto.getPageNo(), codeModelDto.getPageSize());
        List<CodeModel> codeModel = null;
        try {
            codeModel = idDao.selectList(codeModelDto);
        } catch (Exception e) {
            logger.error("查询CodeModel列表信息失败！,codeModelDto:", e);
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "查询CodeModel列表信息失败！");
        }
        return new PageModel<CodeModel>().getPageList(codeModel);
    }

    private CodeModel buildInitialCodeModel(String type, Integer length) {
        CodeModel model = new CodeModel();
        model.setType(type);
        model.setCodeLength(length);
        //新配置发号开始值
        model.setCurrent(getSetInitial(length));
        Date now = new Date();
        model.setCreateDate(now);
        model.setLastUpdateDate(now);
        return model;
    }

    private long getSetInitial(int index) {
        int pow = (int) Math.pow(10, index - 1);
        return (long) (Math.random() * pow + pow);
    }

}

package com.yryz.writer.modules.id.web;


import com.google.common.collect.Lists;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.id.service.IdGenerator;
import com.yryz.writer.modules.id.service.IdService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/10
 * @description
 */
@Service("idAPI")
public class IdAPIImpl implements IdAPI {
    private static final Logger logger = LoggerFactory.getLogger(IdAPIImpl.class);

    @Autowired
    private IdService idService;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public Long getId(String type) {
        Long id = null;
        try {
            if (StringUtils.isBlank(type)) {
                throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                        ExceptionEnum.BusiException.getMsg(),
                        "type can not be blank");
            }
            id = idService.getId(type);
            if (id == null) {
                throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                        ExceptionEnum.BusiException.getMsg(),
                        "getId error");
            }
            logger.info("getId input type: {}, result: {}", type, id);
            return id;
        } catch (Exception e) {
            logger.error("getId error", e);
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "getId error");
        }

    }

    @Override
    public Long getSnowflakeId() {
        Long orderId = null;
        try {
            orderId = idGenerator.getID();
            logger.info("getSnowflakeId result:{}", orderId);
        } catch (Exception e) {
            logger.error("getSnowflakeId error", e);
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "getSnowflakeId error");
        }
        return orderId;
    }

    /**
     * 返回指定个数的id
     * 基于Twitter的分布式自增ID算法Snowflake实现分布式有序
     *
     * @param num
     * @return
     */
    @Override
    public List<Long> getSnowflakeIds(Integer num) {
        List<Long> result = Lists.newArrayList();
        if (num == null || num < 0) {
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "num should be > 0");
        }
        try {
            for (int i = 0; i < num; i++) {
                result.add(getSnowflakeId());
            }
        } catch (Exception e) {
            logger.error("getSnowflakeIds error", e);
            throw new YyrzPcException(ExceptionEnum.BusiException.getCode(),
                    ExceptionEnum.BusiException.getMsg(),
                    "getSnowflakeIds error");
        }
        logger.info("getSnowflakeIds result:{}", result);
        return result;
    }

}

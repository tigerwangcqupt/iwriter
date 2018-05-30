package com.yryz.writer.modules.config.service.impl;

import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.modules.config.dao.persistence.ConfigDao;
import com.yryz.writer.modules.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.config.service.impl
 * @Desc:
 * @Date: 2018/5/29.
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    protected ConfigDao getDao() {
        return configDao;
    }

}

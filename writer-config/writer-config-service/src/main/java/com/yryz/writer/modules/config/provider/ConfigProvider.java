package com.yryz.writer.modules.config.provider;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.config.api.ConfigApi;
import com.yryz.writer.modules.config.entity.Config;
import com.yryz.writer.modules.config.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.config.provider
 * @Desc:
 * @Date: 2018/5/29.
 */
@Service
public class ConfigProvider implements ConfigApi {
    private static final Logger logger = LoggerFactory.getLogger(ConfigProvider.class);

    @Autowired
    private ConfigService configService;

    @Override
    public RpcResponse<List<Config>> getConfigList(String commentType) {
        Config config = new Config();
        config.setCommentType(commentType);
        try {
            return ResponseModel.returnListSuccess(configService.getList(config));
        } catch (Exception e) {
            logger.error("获取config配置失败.commentType=" + commentType, e);
            return ResponseModel.returnException(e);
        }
    }
}

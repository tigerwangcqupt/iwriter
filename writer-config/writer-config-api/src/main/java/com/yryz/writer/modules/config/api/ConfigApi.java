package com.yryz.writer.modules.config.api;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.config.entity.Config;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.config.api
 * @Desc:
 * @Date: 2018/5/29.
 */
public interface ConfigApi {

    /**
     * 获取Config配置集合
     *
     * @param commentType 类型
     * @return
     */
    RpcResponse<List<Config>> getConfigList(String commentType);
}

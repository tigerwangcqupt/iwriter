package com.yryz.openapi.config.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.config.api.ConfigApi;
import com.yryz.writer.modules.config.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("services/app/v1/config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigApi configApi;

    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/trainMode", method = RequestMethod.GET)
    public RpcResponse<List<Config>> trainMode() {
        return configApi.getConfigList("trainMode");
    }

    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/trainTime", method = RequestMethod.GET)
    public RpcResponse<List<Config>> trainTime() {
        return configApi.getConfigList("trainTime");
    }

    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/specialty", method = RequestMethod.GET)
    public RpcResponse<List<Config>> specialty() {
        return configApi.getConfigList("specialty");
    }
}

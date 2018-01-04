package com.yryz.openapi.fans.web;

import com.yryz.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.fans.vo.FansVo;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.FansApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/fans")
public class FansController extends BaseController {
    @Autowired
    private FansApi fansApi;

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public RpcResponse<FansVo> detail(Long fansId) {
        return fansApi.detail(fansId);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RpcResponse<PageList<FansVo>> list(FansDto fansDto) {
        String userId = request.getHeader("userId");
        Assert.notNull(userId, "用户id不能为空");
        fansDto.setWriterId(Long.valueOf(userId));
        return fansApi.list(fansDto);
    }

}
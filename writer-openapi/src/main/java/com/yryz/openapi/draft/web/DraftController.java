package com.yryz.openapi.draft.web;

import com.yryz.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.DraftApi;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.DraftVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/draft")
public class DraftController extends BaseController {
    @Autowired
    private DraftApi draftApi;

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public RpcResponse<DraftVo> detail(Long draftId) {
        return draftApi.detail(draftId);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RpcResponse<PageList<DraftVo>> list(DraftDto draftDto) {
        String userId = request.getHeader("userId");
        Assert.notNull(userId, "用户id不能为空");
        draftDto.setCreateUserId(userId);
        return draftApi.list(draftDto);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RpcResponse<Integer> add(@RequestBody Draft draft) {
        String userId = request.getHeader("userId");
        Assert.notNull(userId, "用户id不能为空");
        draft.setCreateUserId(userId);
        return draftApi.add(draft);
    }

}

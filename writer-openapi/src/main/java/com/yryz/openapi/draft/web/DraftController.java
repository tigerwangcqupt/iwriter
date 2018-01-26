package com.yryz.openapi.draft.web;

import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.DraftApi;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.task.vo.AppVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("services/app/v1/draft")
public class DraftController extends BaseController {
    @Autowired
    private DraftApi draftApi;

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public RpcResponse<DraftVo> detailInfo(@RequestParam("kid") Long kid) {
        return draftApi.detailInfo(kid);
    }

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public RpcResponse<DraftVo> detail(@RequestParam("kid") Long kid) {
        return draftApi.detail(kid);
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
    public RpcResponse<Long> add(@RequestBody Draft draft) {
        String userId = request.getHeader("userId");
        Assert.notNull(userId, "用户id不能为空");
        draft.setCreateUserId(userId);
        return draftApi.add(draft);
    }

    @ResponseBody
    @RequestMapping(value = "/searchApp", method = RequestMethod.GET)
    public RpcResponse<List<AppVo>> selectAppByAppliName(@RequestParam("appliName") String appliName) {
        return draftApi.selectAppByAppliName(appliName);
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public RpcResponse<Integer> del(@RequestParam("kid") Long kid) {
        RpcResponse<Integer> del = draftApi.del(kid);
        return del;
    }

}

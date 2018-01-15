package com.yryz.openapi.task.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.task.TaskApi;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/task")
public class TaskController extends BaseController {
    @Autowired
    private TaskApi taskApi;

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public RpcResponse<TaskVo> detail(@RequestParam("kid") Long kid) {
        return taskApi.detail(kid);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RpcResponse<PageList<TaskVo>> list(TaskDto taskDto) {
        String userId = request.getHeader("userId");
        taskDto.setWriterId(Long.valueOf(userId));
        //app端进入
        taskDto.setAppOrAdmin(0);
        return taskApi.list(taskDto);
    }

    @ResponseBody
    @RequestMapping(value = "/acceptTask", method = RequestMethod.POST)
    public RpcResponse<Boolean> acceptTask(@RequestParam("kid") Long kid) {
        return taskApi.acceptTask(kid);
    }

}

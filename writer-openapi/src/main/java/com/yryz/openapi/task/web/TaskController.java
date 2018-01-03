package com.yryz.openapi.task.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.task.vo.TaskVo;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.TaskApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/task")
public class TaskController {
   @Autowired
   private TaskApi taskApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<TaskVo> detail(Long taskId) {
       return taskApi.detail(taskId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<TaskVo>> list(TaskDto taskDto) {
        return taskApi.list(taskDto);
   }

}

package com.yryz.writer.modules.task.service;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: TaskService
  * @Description: Task业务访问接口
  * @author luohao
  * @date 2018-01-02 20:07:17
  *
 */
@Repository
public interface TaskService extends BaseService {

   PageList<TaskVo> selectList(TaskDto taskDto);

   TaskVo detail(Long taskId);

    RpcResponse<Boolean> acceptTask(Long kid);

    RpcResponse<Integer> add(Task task);

    RpcResponse<Integer> edit(Task task);

    RpcResponse<List<Long>> taskIdList();
}
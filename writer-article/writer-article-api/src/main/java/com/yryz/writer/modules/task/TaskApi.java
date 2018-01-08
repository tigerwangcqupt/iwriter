package com.yryz.writer.modules.task;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.task.vo.TaskVo;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.entity.Task;

/**
 * 
 * @ClassName: TaskApi
 * @Description: TaskApi接口
 * @author luohao
 * @date 2018-01-02 20:07:17
 *
 */
public interface TaskApi {

	/**
	*  获取Task明细
	*  @param  id
	*  @return
	* */
	RpcResponse<Task> get(Long id);

    /**
    *  获取Task明细
    *  @param  id
    *  @return
    * */
    RpcResponse<TaskVo> detail(Long id);

    /**
    * 获取Task列表
    * @param taskDto
    * @return
    * */
    RpcResponse<PageList<TaskVo>> list(TaskDto taskDto);

	RpcResponse<Boolean> acceptTask(Long kid);
}

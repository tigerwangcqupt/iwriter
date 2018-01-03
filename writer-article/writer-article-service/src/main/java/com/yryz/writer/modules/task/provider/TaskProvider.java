package com.yryz.writer.modules.task.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.task.TaskApi;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.vo.TaskVo;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskProvider implements TaskApi {

	private static final Logger logger = LoggerFactory.getLogger(TaskProvider.class);

	@Autowired
	private TaskService taskService;

	/**
	*  获取Task明细
	*  @param  taskId
	*  @return
	* */
	public RpcResponse<Task> get(Long taskId) {
		try {
			return ResponseModel.returnObjectSuccess(taskService.get(Task.class, taskId));
		} catch (Exception e) {
			logger.error("获取Task明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Task明细
	*  @param  taskId
	*  @return
	* */
	public RpcResponse<TaskVo> detail(Long taskId) {
		try {
			return ResponseModel.returnObjectSuccess(taskService.detail(taskId));
		} catch (Exception e) {
			logger.error("获取Task明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取Task列表
    * @param taskDto
    * @return
    *
	*/
    public RpcResponse<PageList<TaskVo>> list(TaskDto taskDto) {
        try {
			 return ResponseModel.returnListSuccess(taskService.selectList(taskDto));
        } catch (Exception e) {
        	logger.error("获取Task列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

}

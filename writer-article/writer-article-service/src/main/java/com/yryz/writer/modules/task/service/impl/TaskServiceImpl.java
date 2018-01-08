package com.yryz.writer.modules.task.service.impl;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.task.dao.persistence.TaskDao;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.service.TaskService;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl extends BaseServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private MessageApi messageApi;

    protected BaseDao getDao() {
        return taskDao;
    }

    public PageList<TaskVo> selectList(TaskDto taskDto) {
        PageUtils.startPage(taskDto.getCurrentPage(), taskDto.getPageSize());
        //清除缓存中的任务数记录
        messageApi.cleanMessageTips(ModuleEnum.PLATFORM, taskDto.getWriterId());

        List<Task> list = taskDao.selectList(taskDto);
        List<TaskVo> taskVoList = new ArrayList<TaskVo>();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                TaskVo taskVo = new TaskVo();
                taskVo.setKid(task.getKid());
                taskVo.setTitle(task.getTitle());
                taskVo.setDraftFee(task.getDraftFee());
                taskVo.setAcceptTaskNum(task.getAcceptTaskNum());
                taskVo.setStartDate(task.getStartDate());
                //聚合应用数据
                Long appId = task.getAppId();
                TaskVo app = taskDao.selectAppById(appId);
                taskVo.setAppliName(app.getAppliName());
                taskVo.setIcon(app.getIcon());
                taskVoList.add(taskVo);
            }
        }
        return new PageModel<TaskVo>().getPageList(taskVoList);
    }


    public TaskVo detail(Long taskId) {
        Task task = taskDao.selectByKid(Task.class, taskId);
        TaskVo taskVo = new TaskVo();
        if (task != null) {
            //Task to TaskVo
            taskVo.setKid(task.getKid());
            taskVo.setDraftFee(task.getDraftFee());
            taskVo.setAppId(task.getAppId());
            taskVo.setEndDate(task.getEndDate());
            taskVo.setDraftType(task.getDraftType());
            taskVo.setAcceptTaskNum(task.getAcceptTaskNum());
            taskVo.setStartDate(task.getStartDate());
            taskVo.setTitle(task.getTitle());
            taskVo.setContentHtml(task.getContentHtml());
            taskVo.setTaskCloseNum(task.getTaskCloseNum());
            //聚合应用数据
            Long appId = task.getAppId();
            TaskVo app = taskDao.selectAppById(appId);
            taskVo.setAppliName(app.getAppliName());
            taskVo.setIcon(app.getIcon());
            taskVo.setCompanyName(app.getCompanyName());
            //根据taskKid查询稿件表,过滤重复写手,得出投稿人数
            Integer submitNum = selectSubmitNum(taskId);
            taskVo.setSubmitNum(submitNum);

        }
        return taskVo;
    }

    @Override
    public RpcResponse<Boolean> acceptTask(Long kid) {
        int i = taskDao.acceptTask(kid);
        return ResponseModel.returnObjectSuccess(true);
    }

    public Integer selectSubmitNum(Long kid) {
        return taskDao.selectSubmitNum(kid);

    }
}

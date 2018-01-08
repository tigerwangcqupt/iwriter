package com.yryz.writer.modules.task.service.impl;

import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.task.vo.TaskVo;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.dao.persistence.TaskDao;
import com.yryz.writer.modules.task.service.TaskService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl extends BaseServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    protected BaseDao getDao() {
        return taskDao;
    }

    public PageList<TaskVo> selectList(TaskDto taskDto) {
        PageUtils.startPage(taskDto.getCurrentPage(), taskDto.getPageSize());
        List<Task> list = taskDao.selectList(taskDto);
        List<TaskVo> taskVoList = new ArrayList<TaskVo>();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                TaskVo taskVo = new TaskVo();
                taskVo.setId(task.getId());
                taskVo.setDraftFee(task.getDraftFee());
                taskVo.setAcceptTaskNum(task.getAcceptTaskNum());
                taskVo.setStartDate(task.getStartDate());
                Long appId = task.getAppId();
                //聚合应用数据
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
        if (taskVo != null) {
            //Task to TaskVo
        }
        return taskVo;
    }
}

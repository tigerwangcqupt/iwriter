package com.yryz.writer.modules.task.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
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

    public PageList<TaskVo> selectList(TaskDto taskDto){
        PageUtils.startPage(taskDto.getCurrentPage(), taskDto.getPageSize());
        List<Task> list = taskDao.selectList(taskDto);
        List<TaskVo> taskVoList = new ArrayList <TaskVo>();
        if(list != null && list.size() > 0) {
            for(Task task : list){
                TaskVo taskVo = new TaskVo();
                //Task to TaskVo
                taskVoList.add(taskVo);
            }
        }
        return new PageModel<TaskVo>().getPageList(taskVoList);
    }


    public TaskVo detail(Long taskId) {
        Task task = taskDao.selectByKid(Task.class,taskId);
        TaskVo taskVo = new TaskVo();
        if (taskVo != null) {
            //Task to TaskVo
        }
        return taskVo;
    }
 }

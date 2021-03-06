package com.yryz.writer.modules.task.service.impl;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.task.dao.persistence.TaskDao;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.service.TaskService;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TaskServiceImpl extends BaseServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private MessageApi messageApi;
    @Autowired
    private IdAPI idAPI;

    protected BaseDao getDao() {
        return taskDao;
    }

    public PageList<TaskVo> selectList(TaskDto taskDto) {
        PageUtils.startPage(taskDto.getCurrentPage(), taskDto.getPageSize());
        //平台任务的已查阅数
        if (taskDto.getAppOrAdmin() != null && taskDto.getAppOrAdmin() == 0) {
            messageApi.setPlatformTaskLooked(taskDto.getWriterId());
        }

        List<Task> list = taskDao.selectList(taskDto);
        List<TaskVo> taskVoList = new ArrayList<TaskVo>();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                TaskVo taskVo = toTaskVo(task);
                taskVoList.add(taskVo);
            }
        }
        return new PageModel<TaskVo>().getPageList(list, taskVoList);
    }

    @Override
    public List<TaskVo> selectAllList(TaskDto taskDto) {
        //平台任务的已查阅数
        if (taskDto.getAppOrAdmin() != null && taskDto.getAppOrAdmin() == 0) {
            messageApi.setPlatformTaskLooked(taskDto.getWriterId());
        }
        List<Task> list = taskDao.selectList(taskDto);
        List<TaskVo> taskVoList = new ArrayList<TaskVo>();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                TaskVo taskVo = toTaskVo(task);
                taskVoList.add(taskVo);
            }
        }
        return taskVoList;
    }


    public TaskVo detail(Long taskId) {
        Task task = taskDao.selectByKid(Task.class, taskId);
        TaskVo taskVo = null;
        if (task != null) {
            taskVo = toTaskVo(task);
        }
        return taskVo;
    }

    @Override
    public RpcResponse<Boolean> acceptTask(Long kid) {
        try {
            Task task = taskDao.selectByKid(Task.class, kid);
            Integer integer = taskDao.selectSubmitWriterNum(kid);
            if (integer != null && integer >= task.getTaskCloseNum()) {
                throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
            }
            if (task.getTaskCloseFlag() != null && task.getTaskCloseFlag() == 1) {
                throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
            }
            if (task.getEndDate() != null) {
                Date date = new Date();
                Date endDate = task.getEndDate();
                if (date.after(endDate)) {
                    throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
                }
            }
            int i = taskDao.acceptTask(kid);
            return ResponseModel.returnObjectSuccess(true);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RpcResponse<Integer> add(Task task) {
        Long kid = idAPI.getId("yryz_task");
        task.setKid(kid);
        int insert = taskDao.insertByPrimaryKeySelective(task);
        return ResponseModel.returnObjectSuccess(insert);
    }

    @Override
    public RpcResponse<Integer> edit(Task task) {
        int update = taskDao.update(task);
        return ResponseModel.returnObjectSuccess(update);
    }

    @Override
    public RpcResponse<List<Long>> taskIdList() {
        return ResponseModel.returnObjectSuccess(taskDao.taskIdList());
    }

    public Integer selectSubmitWriterNum(Long kid) {
        return taskDao.selectSubmitWriterNum(kid);

    }

    public Integer selectSubmitDraftNum(Long kid) {
        return taskDao.selectSubmitDraftNum(kid);

    }

    private TaskVo toTaskVo(Task task) {
        TaskVo taskVo = new TaskVo();
        taskVo.setKid(task.getKid());
        taskVo.setDraftFee(task.getDraftFee());
        taskVo.setAppId(task.getAppId());
        taskVo.setEndDate(task.getEndDate());
        taskVo.setDraftType(task.getDraftType());
        taskVo.setAcceptTaskNum(task.getAcceptTaskNum());
        taskVo.setStartDate(task.getStartDate());
        taskVo.setCreateDate(task.getCreateDate());
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
        Integer WriterNum = selectSubmitWriterNum(task.getKid());
        taskVo.setWriterNum(WriterNum);
        Integer draftNum = selectSubmitDraftNum(task.getKid());
        taskVo.setDraftNum(draftNum);

        //判断任务是否过期,任务过期将taskStatus置为1
        if (WriterNum != null && WriterNum >= task.getTaskCloseNum()) {
            taskVo.setTaskStatus(1);
        }
        if (task.getTaskCloseFlag() != null && task.getTaskCloseFlag() == 1) {
            taskVo.setTaskStatus(1);
        }
        if (task.getEndDate() != null) {
            Date date = new Date();
            Date endDate = task.getEndDate();
            if (date.after(endDate)) {
                taskVo.setTaskStatus(1);
            }else if(null != task.getStartDate() && task.getStartDate().after(date)){
                taskVo.setTaskStatus(2);
            }
        }

        return taskVo;
    }
}

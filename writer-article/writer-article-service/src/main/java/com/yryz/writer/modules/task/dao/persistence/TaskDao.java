package com.yryz.writer.modules.task.dao.persistence;

import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.common.dao.BaseDao;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luohao
 * @ClassName: TaskDao
 * @Description: Task数据访问接口
 * @date 2018-01-02 20:07:17
 */
@Repository
public interface TaskDao extends BaseDao {

    List<Task> selectList(TaskDto taskDto);

    //根据id查询应用id,应用名,应用icon
    TaskVo selectAppById(@Param("id") Long id);

}
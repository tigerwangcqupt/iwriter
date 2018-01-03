package com.yryz.writer.modules.task.dao.persistence;

import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.dto.TaskDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: TaskDao
  * @Description: Task数据访问接口
  * @author luohao
  * @date 2018-01-02 20:07:17
  *
 */
@Repository
public interface TaskDao extends BaseDao {

    List<Task> selectList(TaskDto taskDto);

}
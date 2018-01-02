package com.yryz.writer.modules.draft.dao.persistence;

import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: DraftDao
  * @Description: Draft数据访问接口
  * @author luohao
  * @date 2017-12-29 14:40:13
  *
 */
@Repository
public interface DraftDao extends BaseDao {

    List<Draft> selectList(DraftDto draftDto);

}
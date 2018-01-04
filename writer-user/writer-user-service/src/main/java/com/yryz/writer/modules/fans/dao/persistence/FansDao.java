package com.yryz.writer.modules.fans.dao.persistence;

import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.common.dao.BaseDao;
import com.yryz.writer.modules.fans.vo.FansVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: FansDao
  * @Description: Fans数据访问接口
  * @author luohao
  * @date 2018-01-02 20:08:19
  *
 */
@Repository
public interface FansDao extends BaseDao {

    List<Fans> selectList(FansDto fansDto);

    FansVo selectUserById(@Param("id")Long id);

}
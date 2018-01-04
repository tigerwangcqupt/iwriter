package com.yryz.writer.modules.profit.dao.persistence;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.common.dao.BaseDao;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: ProfitDao
  * @Description: Profit数据访问接口
  * @author zhongying
  * @date 2017-12-29 15:36:15
  *
 */
@Repository
public interface ProfitDao extends BaseDao {

    List<Profit> selectList(ProfitDto profitDto);

}
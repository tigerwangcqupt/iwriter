package com.yryz.writer.modules.indexcolumn.service;

import com.yryz.common.service.BaseService;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.message.vo.IndexTipsVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * 
  * @ClassName: IndexColumnService
  * @Description: IndexColumn业务访问接口
  * @author huyangyang
  * @date 2018-01-02 10:04:46
  *
 */
@Repository
public interface IndexColumnService extends BaseService {

   PageList<IndexColumnVo> selectList(IndexColumnDto indexColumnDto);

   IndexColumnVo detail(Long indexColumnId);

   /**
    * 查询未删除的全部栏目
    * @return
    */
   public IndexColumnVo selectAll(IndexColumnDto indexColumnDto);

   /**
    * 获得写手的消息栏目（包含每个栏目的消息数）
    * @param indexColumnDto
    * @return
    */
   public List<IndexTipsVo> getIndexTips(IndexColumnDto indexColumnDto);
}
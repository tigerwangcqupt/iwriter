package com.yryz.writer.modules.writer.dao.persistence;

import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.writer.dto.WriterDto;

import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterAdminVo;
import com.yryz.writer.modules.writer.vo.WriterModelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterDao
  * @Description: Writer数据访问接口
  * @author liuyanjun
  * @date 2018-01-03 15:03:10
  *
 */
@Repository
public interface WriterDao extends BaseDao {

    List<Writer> selectList(WriterDto writerDto);
    
    List<Writer> checkNickName(Writer writer);

    List<WriterAdminVo> selectWriterList(WriterDto writerDto);
    
    WriterAdminVo selectWriterDetail(@Param("kid")Long kid);
    
    Integer updateWriter(WriterAdminVo writerAdminVo);
    
    Writer selectByPhone(String phone);

    List<Writer> selectAdminList(WriterDto writerDto);

    WriterModelVo selectWriterByParameters(WriterDto writerDto);

    /**
     * 后台查询写手收益列表
     * @param writerDto
     * @return
     */
    List<WriterAdminRefProfit> selectAdminProfitList(WriterDto writerDto);

    Integer updateWriterProfit(Writer writer);

}
package com.yryz.writer.modules.trian.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.vo.WriterTrianVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.config.dao.persistence
 * @Desc:
 * @Date: 2018/5/29.
 */
@Repository
public interface WriterTrianDao extends BaseDao {

    List<WriterTrianVo> getList(WriterTrianDto writerTrianDto);

    int getCount(WriterTrianDto writerTrianDto);

}

package com.yryz.writer.modules.ad.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.dao.persistence
 * @Desc:
 * @Date: 2018/6/1.
 */
@Repository
public interface AdDao extends BaseDao {

    List<Ad> getList(AdDto adDto);

    Integer getMaxSort();
}

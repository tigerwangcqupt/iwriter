package com.yryz.writer.modules.province.dao.persistence;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yryz.writer.modules.province.dto.ProvinceDto;
import com.yryz.writer.modules.province.entity.Province;
import com.yryz.writer.modules.province.vo.ProvinceVo;

import java.util.List;

/**
 * 
  * @ClassName: ProvinceDao
  * @Description: Province数据访问接口
  * @author wangsenyong
  * @date 2017-09-20 10:48:53
  *
 */
@Repository
public interface ProvinceDao {

    public List<ProvinceVo> queryAllProvinces();
    
    public Province selectProvince(@Param("provinceCode") String provinceCode);
}
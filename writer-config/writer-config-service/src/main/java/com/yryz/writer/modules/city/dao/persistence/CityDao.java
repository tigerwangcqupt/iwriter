package com.yryz.writer.modules.city.dao.persistence;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yryz.writer.modules.city.dto.CityDto;
import com.yryz.writer.modules.city.entity.City;
import com.yryz.writer.modules.city.vo.CityVo;

import java.util.List;

/**
 * 
  * @ClassName: CityDao
  * @Description: City数据访问接口
  * @author wangsenyong
  * @date 2017-09-20 10:53:48
  *
 */
@Repository
public interface CityDao {

    List<City> selectList(CityDto cityDto);

    Integer deleteByPrimaryKey(Long id);

    Integer insert(City city);
    
 	Integer insertByPrimaryKeySelective(City city);
 
    City selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(City city);
    
    List<CityVo> selectCitysByPid(@Param("provinceCode") String provinceCode);
}
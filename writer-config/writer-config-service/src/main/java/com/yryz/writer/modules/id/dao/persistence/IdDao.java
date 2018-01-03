package com.yryz.writer.modules.id.dao.persistence;


import com.yryz.writer.modules.id.entity.CodeModel;
import com.yryz.writer.modules.id.entity.CodeModelDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/14
 * @description
 */
@Repository
public interface IdDao {

    /**
     * 根据业务类型type查询code配置
     * @param type
     * @return
     */
    CodeModel selectByType(String type);

    /**
     * 新业务类型type生成code配置
     * @param model
     * @return
     */
    int insertCodeModel(CodeModel model);

    /**
     * 给业务类型type生成code配置
     * @param type
     * @param current
     * @return
     */
    int updateByType(@Param("type") String type, @Param("current") Long current);


    List<CodeModel> selectList(CodeModelDto codeModelDto);

}

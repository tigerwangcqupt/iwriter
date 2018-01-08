package com.yryz.writer.modules.draft.dao.persistence;

import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luohao
 * @ClassName: DraftDao
 * @Description: Draft数据访问接口
 * @date 2017-12-29 14:40:13
 */
@Repository
public interface DraftDao extends BaseDao {

    //全部
    List<Draft> selectList(DraftDto draftDto);

    //已发表
    List<Draft> selectPublish(DraftDto draftDto);

    //未通过
    List<Draft> selectNotPass(DraftDto draftDto);

    //草稿
    List<Draft> selectDraught(DraftDto draftDto);

}
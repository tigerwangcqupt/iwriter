package com.yryz.writer.modules.draft.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.draft.entity.DraftAudit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/5/30 14:49
 * Created by huangxy
 */
@Repository
public interface DraftAuditDao extends BaseDao{

    List<DraftAudit> selectByDraftKids(@Param("kids") List<Long> kids);

}

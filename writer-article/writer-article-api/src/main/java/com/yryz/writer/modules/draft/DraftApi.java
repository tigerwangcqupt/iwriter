package com.yryz.writer.modules.draft;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.draft.entity.Draft;

/**
 * @author luohao
 * @ClassName: DraftApi
 * @Description: DraftApi接口
 * @date 2017-12-29 14:40:13
 */
public interface DraftApi {

    /**
     * 获取Draft明细
     *
     * @param id
     * @return
     */
    RpcResponse<Draft> get(Long id);

    /**
     * 获取稿件明细
     *
     * @param id
     * @return
     */
    RpcResponse<DraftVo> detail(Long id);

    /**
     * 获取稿件列表
     *
     * @param draftDto
     * @return
     */
    RpcResponse<PageList<DraftVo>> list(DraftDto draftDto);

    /**
     * 发布稿件
     *
     * @param draft
     * @return
     */
    RpcResponse<Integer> add(Draft draft);

    RpcResponse<Integer> check(Draft draft);

}

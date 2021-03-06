package com.yryz.writer.modules.draft;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.UserVo;
import com.yryz.writer.modules.task.vo.AppVo;

import java.util.List;

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
    RpcResponse<Long> add(Draft draft);

    RpcResponse<Integer> check(Draft draft);

    RpcResponse<List<AppVo>> selectAppByAppliName(String appliName);

    RpcResponse<Integer> del(Long kid);

    RpcResponse<List<UserVo>> selectUserByUserName(String userName);


    RpcResponse<DraftVo> detailInfo(Long kid);


    /**
     * 设置审核人员
     * @param kids              稿件
     * @param auditorUserId     审核人员
     * @param assignerUserId    派稿人员
     * @return
     */
    RpcResponse<Integer> setAuditorUser(List<Long> kids,String auditorUserId,String assignerUserId);
}

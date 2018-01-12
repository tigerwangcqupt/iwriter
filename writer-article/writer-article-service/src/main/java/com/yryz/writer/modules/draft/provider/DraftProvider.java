package com.yryz.writer.modules.draft.provider;

import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.draft.DraftApi;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.service.DraftService;

import com.yryz.writer.modules.task.vo.AppVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DraftProvider implements DraftApi {

    private static final Logger logger = LoggerFactory.getLogger(DraftProvider.class);

    @Autowired
    private DraftService draftService;

    /**
     * 获取Draft明细
     *
     * @param draftId
     * @return
     */
    public RpcResponse<Draft> get(Long draftId) {
        try {
            return ResponseModel.returnObjectSuccess(draftService.get(Draft.class, draftId));
        } catch (Exception e) {
            logger.error("获取Draft明细失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 获取Draft明细
     *
     * @param draftId
     * @return
     */
    public RpcResponse<DraftVo> detail(Long draftId) {
        try {
            return ResponseModel.returnObjectSuccess(draftService.detail(draftId));
        } catch (Exception e) {
            logger.error("获取Draft明细失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 获取Draft列表
     *
     * @param draftDto
     * @return
     */
    public RpcResponse<PageList<DraftVo>> list(DraftDto draftDto) {
        try {
            return ResponseModel.returnListSuccess(draftService.selectList(draftDto));
        } catch (Exception e) {
            logger.error("获取Draft列表失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 发布稿件
     *
     * @param draft
     * @return
     */
    public RpcResponse<Long> add(Draft draft) {
        try {
            return ResponseModel.returnObjectSuccess(draftService.add(draft));
        } catch (Exception e) {
            logger.error("发布稿件失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Integer> check(Draft draft) {
        return ResponseModel.returnObjectSuccess(draftService.update(draft));
    }

    /**
     * 查询应用
     */
    public RpcResponse<List<AppVo>> selectAppByAppliName(String appliName) {
        return ResponseModel.returnListSuccess(draftService.selectAppByAppliName(appliName));
    }

    public void del(Long kid) {
        draftService.delete(kid);
    }

}

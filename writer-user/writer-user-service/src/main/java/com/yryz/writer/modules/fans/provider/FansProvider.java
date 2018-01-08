package com.yryz.writer.modules.fans.provider;

import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.fans.FansApi;
import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.vo.FansVo;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.service.FansService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FansProvider implements FansApi {

    private static final Logger logger = LoggerFactory.getLogger(FansProvider.class);

    @Autowired
    private FansService fansService;

    /**
     * 获取Fans明细
     *
     * @param fansId
     * @return
     */
    public RpcResponse<Fans> get(Long fansId) {
        try {
            return ResponseModel.returnObjectSuccess(fansService.get(Fans.class, fansId));
        } catch (Exception e) {
            logger.error("获取Fans明细失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 获取Fans明细
     *
     * @param fansId
     * @return
     */
    public RpcResponse<FansVo> detail(Long fansId) {
        try {
            return ResponseModel.returnObjectSuccess(fansService.detail(fansId));
        } catch (Exception e) {
            logger.error("获取Fans明细失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 获取Fans列表
     *
     * @param fansDto
     * @return
     */
    public RpcResponse<PageList<FansVo>> list(FansDto fansDto) {
        try {
            return ResponseModel.returnListSuccess(fansService.selectList(fansDto));
        } catch (Exception e) {
            logger.error("获取Fans列表失败", e);
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 获取新增粉丝列表列表
     *
     * @param fansDto
     * @return
     */
    public RpcResponse<PageList<FansVo>> NewFansList(FansDto fansDto) {
        try {
            return ResponseModel.returnListSuccess(fansService.selectNewFans(fansDto));
        } catch (Exception e) {
            logger.error("获取Fans列表失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Integer> selectCount(Long userId) {
        try {
            return ResponseModel.returnListSuccess(fansService.selectCount(userId));
        } catch (Exception e) {
            logger.error("获取全部粉丝数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Integer> selectNewFansCount(Long userId) {
        try {
            return ResponseModel.returnListSuccess(fansService.selectNewFansCount(userId));
        } catch (Exception e) {
            logger.error("获取新增粉丝数失败", e);
            return ResponseModel.returnException(e);
        }
    }

}

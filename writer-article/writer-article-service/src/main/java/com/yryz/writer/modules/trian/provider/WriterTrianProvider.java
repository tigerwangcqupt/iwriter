package com.yryz.writer.modules.trian.provider;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.redis.utils.StringUtils;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.trian.api.WriterTrianApi;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.entity.WriterTrian;
import com.yryz.writer.modules.trian.service.WriterTrianService;
import com.yryz.writer.modules.trian.vo.WriterTrianVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.provider
 * @Desc:
 * @Date: 2018/5/29.
 */
@Service
public class WriterTrianProvider implements WriterTrianApi {
    private static final Logger logger = LoggerFactory.getLogger(WriterTrianProvider.class);

    @Autowired
    private WriterTrianService writerTrianService;

    @Autowired
    private IdAPI idAPI;

    @Override
    public RpcResponse<PageList<WriterTrianVo>> getList(WriterTrianDto writerTrianDto) {
        try {
            return ResponseModel.returnObjectSuccess(writerTrianService.getList(writerTrianDto));
        } catch (Exception e) {
            logger.error("获取WriterTrian列表失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Integer> getCount(WriterTrianDto writerTrianDto) {
        try {
            return ResponseModel.returnObjectSuccess(writerTrianService.getCount(writerTrianDto));
        } catch (Exception e) {
            logger.error("获取WriterTrian数量失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Integer> insert(WriterTrian writerTrian) {
        try {
            WriterTrianDto writerTrianDto = new WriterTrianDto();
            writerTrianDto.setPhone(writerTrian.getPhone());
            if (writerTrianService.getCount(writerTrianDto) > 0) {
                return ResponseModel.returnException(YyrzPcException.busiError("手机号已报名", "手机号已报名"));
            }
            Long kid = idAPI.getSnowflakeId();
            writerTrian.setKid(kid);
            if (StringUtils.isEmpty(writerTrian.getCreateUserId())) {
                writerTrian.setCreateUserId("");
            }
            return ResponseModel.returnObjectSuccess(writerTrianService.insert(writerTrian));
        } catch (Exception e) {
            logger.error("新增WriterTrian失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<WriterTrian> getByKid(Long kid) {
        try {
            return ResponseModel.returnObjectSuccess(writerTrianService.get(WriterTrian.class, kid));
        } catch (Exception e) {
            logger.error("获取WriterTrian失败", e);
            return ResponseModel.returnException(e);
        }
    }
}

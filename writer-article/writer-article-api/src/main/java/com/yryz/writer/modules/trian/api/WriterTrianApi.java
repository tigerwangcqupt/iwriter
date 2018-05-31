package com.yryz.writer.modules.trian.api;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.entity.WriterTrian;
import com.yryz.writer.modules.trian.vo.WriterTrianVo;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.api
 * @Desc:
 * @Date: 2018/5/29.
 */
public interface WriterTrianApi {

    /**
     * 写手培训-活动报名列表
     *
     * @param writerTrianDto
     * @return
     */
    RpcResponse<PageList<WriterTrianVo>> getList(WriterTrianDto writerTrianDto);

    /**
     * 写手培训-活动报名数量
     *
     * @param writerTrianDto
     * @return
     */
    RpcResponse<Integer> getCount(WriterTrianDto writerTrianDto);

    /**
     * 新增写手培训报名记录
     *
     * @param writerTrian
     * @return
     */
    RpcResponse<Integer> insert(WriterTrian writerTrian);

    /**
     * 根据kid查询写手培训报名记录
     *
     * @param kid
     * @return
     */
    RpcResponse<WriterTrian> getByKid(Long kid);
}

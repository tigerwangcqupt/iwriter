package com.yryz.writer.modules.message;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.MessageNumVo;

/**
 * 
 * @ClassName: MessageApi
 * @Description: 消息接口
 * @author huyangyang
 * @date 2018-01-02 20:52:42
 *
 */
public interface MessageApi {

    /**
     * 获取首页新消息总数
     * @param messageDto
     * @return
     */
    RpcResponse<MessageNumVo> getIndexMessageNum(MessageDto messageDto);


}

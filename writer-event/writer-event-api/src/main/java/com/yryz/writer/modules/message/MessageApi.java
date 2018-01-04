package com.yryz.writer.modules.message;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.IndexTipsVo;
import com.yryz.writer.modules.message.vo.MessageNumVo;

import java.util.List;

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

    /**
     * 获得写手的消息栏目（包含每个栏目的消息数）
     * @param writerId
     * @return
     */
    public RpcResponse<List<IndexTipsVo>> getIndexTips(Long writerId);

    /**
     * 清空某个消息栏目缓存数（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public RpcResponse<Boolean> cleanMessageTips(ModuleEnum moduleEnum, Long writerId);

    /**
     * 增加写手的消息缓存数，每次访问列表时清空（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public RpcResponse<Boolean> saveMessageTips(ModuleEnum moduleEnum, Long writerId);
}
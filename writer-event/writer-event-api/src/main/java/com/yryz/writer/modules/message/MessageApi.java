package com.yryz.writer.modules.message;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.*;

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

    /**
     * 设置写手的消息缓存数
     * @param writerId
     * @return
     */
    public RpcResponse<Boolean> setMessageTips(ModuleEnum moduleEnum, Long writerId, Long messageNum);

    /**
     * 增加平台任务缓存数
     * @return
     */
    public RpcResponse<Boolean> savePlatformTaskMessageTips();

    /**
     * 获取写手的平台任务数
     * @return
     */
    public RpcResponse<Long> getPlatformTaskMessageTips(Long writerId);

    /**
     * 获得写手某个栏目的气泡数
     * @param moduleEnum
     * @param writerId
     * @return
     */
    public RpcResponse<Long> getMessageTipsNum(ModuleEnum moduleEnum, Long writerId);

    /**
     * 保存发送给写手的消息
     * @param writerNoticeMessageVo
     * @return
     */
    public RpcResponse<Boolean> saveWriterNoticeMessage(WriterNoticeMessageVo writerNoticeMessageVo);

    /**
     * 查询发送给写手的消息
     * @param writerNoticeMessageDto
     * @return
     */
    public RpcResponse<PageList<WriterNoticeVo>> queryWriterNoticeMessage(WriterNoticeMessageDto writerNoticeMessageDto);
}

package com.yryz.writer.modules.message.provider;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.service.MessageService;
import com.yryz.writer.modules.message.service.impl.MessageMongo;
import com.yryz.writer.modules.message.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * File Name: MessageProvider
 * Package Name: com.yryz.writer.modules.message.provider
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 10:34
 **/
@Service
public class MessageProvider implements MessageApi {
    private static final Logger logger = LoggerFactory.getLogger(MessageProvider.class);

    @Autowired
    private MessageService messageService;

    @Override
    public RpcResponse<MessageNumVo> getIndexMessageNum(MessageDto messageDto) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.getMessageNumVo(messageDto.getCustId()));
        } catch (Exception e) {
            logger.error("获取首页消息总数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<List<IndexTipsVo>> getIndexTips(Long writerId) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.getIndexTips(writerId));
        } catch (Exception e) {
            logger.error("获得写手的消息栏目失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Boolean> cleanMessageTips(ModuleEnum moduleEnum, Long writerId) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.cleanMessageTips(moduleEnum, writerId));
        } catch (Exception e) {
            logger.error("清空某个消息栏目缓存数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Boolean> saveMessageTips(ModuleEnum moduleEnum, Long writerId) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.saveMessageTips(moduleEnum, writerId));
        } catch (Exception e) {
            logger.error("清空某个消息栏目缓存数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Long> getMessageTipsNum(ModuleEnum moduleEnum, Long writerId) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.getMessageTipsNum(moduleEnum, writerId));
        } catch (Exception e) {
            logger.error("返回某个消息栏目缓存数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Boolean> saveWriterNoticeMessage(WriterNoticeMessageVo writerNoticeMessageVo) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.saveWriterNoticeMessage(writerNoticeMessageVo));
        } catch (Exception e) {
            logger.error("返回某个消息栏目缓存数失败", e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<PageList<WriterNoticeVo>> queryWriterNoticeMessage(WriterNoticeMessageDto writerNoticeMessageDto) {
        try {
            return ResponseModel.returnObjectSuccess(messageService.queryWriterNoticeMessage(writerNoticeMessageDto));
        } catch (Exception e) {
            logger.error("返回某个消息栏目缓存数失败", e);
            return ResponseModel.returnException(e);
        }
    }
}

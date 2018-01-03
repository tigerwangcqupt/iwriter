package com.yryz.writer.modules.message.provider;

import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.service.MessageService;
import com.yryz.writer.modules.message.vo.MessageNumVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            logger.error("获取ArticleFavorite明细失败", e);
            return ResponseModel.returnException(e);
        }
    }
}

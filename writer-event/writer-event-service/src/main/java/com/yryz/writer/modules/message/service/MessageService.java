package com.yryz.writer.modules.message.service;

import com.yryz.writer.modules.message.vo.MessageNumVo;

/**
 * File Name: MessageService
 * Package Name: com.yryz.writer.modules.message.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 10:36
 **/
public interface MessageService {

    /**
     * 获得写手的消息总数（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public MessageNumVo getMessageNumVo(String writerId);

}

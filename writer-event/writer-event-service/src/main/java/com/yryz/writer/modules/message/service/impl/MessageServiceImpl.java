package com.yryz.writer.modules.message.service.impl;

import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.ResponseModel;
import com.yryz.writer.modules.message.service.MessageService;
import com.yryz.writer.modules.message.vo.MessageNumVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * File Name: MessageServiceImpl
 * Package Name: com.yryz.writer.modules.message.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 11:22
 **/
@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    protected BaseDao getDao() {
        return null;
    }


    @Override
    public MessageNumVo getMessageNumVo(String writerId) {
        MessageNumVo messageNumVo = null;
        try {
            //todo
            messageNumVo = new MessageNumVo();
            messageNumVo.setMessageNum("99");
        } catch (Exception e) {
            logger.error("查询写手首页消息总数失败", e);
        }
        return messageNumVo;
    }



}

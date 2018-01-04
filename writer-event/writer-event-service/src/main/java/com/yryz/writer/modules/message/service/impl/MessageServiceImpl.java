package com.yryz.writer.modules.message.service.impl;

import com.yryz.common.dao.BaseDao;
import com.yryz.common.redis.utils.JedisUtils;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.ResponseModel;
import com.yryz.writer.modules.articlefavorite.constant.FavoriteConstant;
import com.yryz.writer.modules.message.constant.MessageConstant;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.service.MessageService;
import com.yryz.writer.modules.message.vo.IndexTipsVo;
import com.yryz.writer.modules.message.vo.MessageNumVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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

    /** 首页消息通知栏目列表  */
    private static final ModuleEnum[] indexModuleEnums = {ModuleEnum.NOTICE,
            ModuleEnum.COMMENT, ModuleEnum.SHARE, ModuleEnum.FAVORITE};

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    protected BaseDao getDao() {
        return null;
    }


    @Override
    public MessageNumVo getMessageNumVo(String writerId) {
        MessageNumVo messageNumVo = null;
        try {
            //todo设置消息总数
            messageNumVo = new MessageNumVo();
            messageNumVo.setMessageNum("99");
        } catch (Exception e) {
            logger.error("查询写手首页消息总数失败", e);
        }
        return messageNumVo;
    }

    public List<IndexTipsVo> getIndexTips(Long writerId){
        List<IndexTipsVo> indexTips = new ArrayList<IndexTipsVo>(indexModuleEnums.length);
        boolean success = true;
        try {
            for (ModuleEnum moduleEnum : indexModuleEnums) {
                String key = MessageConstant.getHashKey(writerId);
                String field = MessageConstant.getHashField(moduleEnum.getValue());
                IndexTipsVo indexTipsVo = new IndexTipsVo();
                indexTipsVo.setColumnName(moduleEnum.getName());
                indexTipsVo.setColumnUrl(moduleEnum.getUrl());
                //如果存在业务的缓存气泡数
                if (JedisUtils.mapExists(key, field)) {
                    String num = JedisUtils.mapHget(key, field);
                    indexTipsVo.setTipsNum(num);
                } else {
                    //从缓存数据库中读取
//                    success = JedisUtils.mapSetnx(key, field, "1");
                      success = false;
                }
                //如果都读取不到
                if (!success){
                    indexTipsVo.setTipsNum("0");
                }
                indexTips.add(indexTipsVo);
            }

        } catch (Exception e) {
            logger.error("保存消息缓存气泡失败", e);
        }
        return indexTips;
    }

    @Override
    public Boolean saveMessageTips(ModuleEnum moduleEnum, Long writerId) {
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(moduleEnum.getValue());
        boolean success = true;
        try {
            //如果存在业务的缓存气泡数
            if (JedisUtils.mapExists(key, field)) {
                //写手的收藏数增加1
                Long status = JedisUtils.mapIncrby(key, field, 1);
                //如果缓存没有设置成功 做什么
                if (status == null) {
                    success = false;

                }
            } else {
                //写手的收藏数设置1
                //是否设置成功
                success = JedisUtils.mapSetnx(key, field, "1");
                if (!success) {
                    //如果缓存没有设置成功 做什么
                }
            }
        } catch (Exception e) {
            logger.error("保存消息缓存气泡失败", e);
        }
        return success;
    }


    @Override
    public Boolean cleanMessageTips(ModuleEnum moduleEnum, Long writerId) {
        Assert.notNull(moduleEnum, "模块不能为空");
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(moduleEnum.getValue());
        boolean success = true;
        try {
            //如果存在业务的缓存气泡数
            if (JedisUtils.mapExists(key, field)) {
                Long status = JedisUtils.mapRemove(key, field);
                if (status == null){
                    success = false;
                }
            }
        } catch (Exception e) {
            logger.error("清空消息缓存气泡失败:" + moduleEnum.getName(), e);
        }
        return success;
    }

}

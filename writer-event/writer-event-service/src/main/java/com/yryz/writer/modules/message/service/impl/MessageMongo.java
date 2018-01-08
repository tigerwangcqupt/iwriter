package com.yryz.writer.modules.message.service.impl;

import com.yryz.service.api.api.exception.MongoOptException;
import com.yryz.service.api.basic.message.MessageVo;
import com.yryz.writer.common.mongodb.AbsBaseMongoDAO;
import com.yryz.writer.common.utils.GsonUtils;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageDto;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageVo;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * File Name: MessageMongo
 * Package Name: com.yryz.writer.modules.message.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-08 10:37
 **/
@Service
public class MessageMongo extends AbsBaseMongoDAO<WriterNoticeMessageVo> {

    private Logger logger = LoggerFactory.getLogger(MessageMongo.class);

//    /**
//     * 列表查询
//     * @param messageVo
//     * @param start
//     * @param limit
//     * @return
//     */
//    public List<MessageVo> getList(MessageVo messageVo , Integer start , Integer limit){
//        Query query = new Query();
//        if(messageVo != null){
//            if( messageVo.getType() != null && messageVo.getType() >= 0 ){
//                query.addCriteria(Criteria.where("type").is(messageVo.getType()));
//            }
//
//            if( messageVo.getLabel() != null && messageVo.getLabel() >= 0 ){
//                query.addCriteria(Criteria.where("label").is(messageVo.getLabel()));
//            }
//
//            if(StringUtils.isNotEmpty(messageVo.getToCust())){
//                query.addCriteria(Criteria.where("toCust").is(messageVo.getToCust()));
//            }
//
//            if(StringUtils.isNotEmpty(messageVo.getMessageId())){
//                query.addCriteria(Criteria.where("messageId").is(messageVo.getMessageId()));
//            }
//        }
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
//        Sort sort = new Sort(order);
//        query.with(sort);
//        query.limit(limit.intValue());
//        query.skip(start.intValue());
//        return find(query);
//    }
//
//    /**
//     * 保存mongo
//     * @param messageVo
//     */
//    public MessageVo save(MessageVo messageVo){
//        try {
//            return super.save(messageVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 保存mongo
     * @param writerNoticeMessageVo
     */
    public Boolean saveWriterNotice(WriterNoticeMessageVo writerNoticeMessageVo){
        try {
            mongoTemplate.save(writerNoticeMessageVo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 分页查询写手通知消息
     * @param writerNoticeMessageDto
     * @return
     */
    public List<WriterNoticeMessageVo> queryWriterNoticePage(WriterNoticeMessageDto writerNoticeMessageDto){
        Query query = new Query();
        if(writerNoticeMessageDto != null){
            if( writerNoticeMessageDto.getReceiveWriterId() != null ){
                query.addCriteria(Criteria.where("receiveWriter.kid").is(writerNoticeMessageDto.getReceiveWriterId()));
                //array多条件 且查询  如下例子
//                query.addCriteria(Criteria.where("receiveWriter").elemMatch(Criteria.where("kid").is(1)).elemMatch(Criteria.where("userNickName").is("接受的写手1")));
            }

        }
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
        Sort sort = new Sort(order);
        query.with(sort);

        query.limit(writerNoticeMessageDto.getPageSize());
        query.skip((writerNoticeMessageDto.getCurrentPage() - 1) * writerNoticeMessageDto.getPageSize());
        return find(query);
    }

//    /**
//     * 删除消息
//     * @param messageId
//     * @return
//     */
//    public int delete(String messageId) {
//        int flag = 0;
//        try {
//            Query query = new Query();
//            query.addCriteria(Criteria.where("messageId").is(messageId));
//            mongoTemplate.findAndRemove(query, MessageVo.class);
//
//            logger.info("[mongo:delete] delete message " + messageId + ", effective dates " + flag);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new MongoOptException("[delete message]", e.getCause());
//        }
//        return flag;
//    }
//
//    /**
//     * 更新消息
//     * @param messageVo
//     */
//    public void update(MessageVo messageVo) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("messageId").is(messageVo.getMessageId()));
//        String obj = GsonUtils.parseJson(messageVo);
//        JSONObject json = new JSONObject(obj);
//
//        Set<String> keys = json.keySet();
//        Update update = new Update();
//        for (String key : keys) {
//            update.set(key, json.get(key));
//        }
//        mongoTemplate.upsert(query, update, MessageVo.class);
//    }
//
//    /**
//     * 单一查询
//     * @param messageId
//     * @return
//     */
//    public MessageVo get(String messageId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("messageId").is(messageId));
//        return (MessageVo)findOne(query);
//    }


    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}

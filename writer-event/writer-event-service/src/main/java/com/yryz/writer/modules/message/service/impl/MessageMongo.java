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


    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}

package com.yryz.openapi.message.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.service.api.basic.message.MessageVo;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.MessageNumVo;
import com.yryz.writer.modules.message.vo.NoticeReceiveWriter;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageDto;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("services/app/v1/message")
public class MessageController {
   @Autowired
   private MessageApi messageApi;


   @ResponseBody
   @RequestMapping(value="/messageNum", method = RequestMethod.GET)
   public RpcResponse<MessageNumVo> getIndexMessageNum(MessageDto messageDto) {
//        return indexColumnApi.list(indexColumnDto);
      return messageApi.getIndexMessageNum(messageDto);
   }

   @ResponseBody
      @RequestMapping(value="/saveMessage", method = RequestMethod.POST)
      public RpcResponse<Boolean> saveMessage(@RequestBody WriterNoticeMessageVo writerNoticeMessageVo) {
//        return indexColumnApi.list(indexColumnDto);
      Assert.notNull(writerNoticeMessageVo.getContent(), "消息内容不能为空");
      Assert.notEmpty(writerNoticeMessageVo.getReceiveWriter(), "消息接受者不能为空");
      return messageApi.saveWriterNoticeMessage(writerNoticeMessageVo);
   }

   @ResponseBody
   @RequestMapping(value="/queryMessage", method = RequestMethod.GET)
   public RpcResponse<PageList<WriterNoticeMessageVo>> queryMessage(WriterNoticeMessageDto writerNoticeMessageDto) {
      return messageApi.queryWriterNoticeMessage(writerNoticeMessageDto);
   }

}

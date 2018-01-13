package com.yryz.openapi.message.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.service.api.basic.message.MessageVo;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.*;
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
public class MessageController extends BaseController {
   @Autowired
   private MessageApi messageApi;


   @ResponseBody
   @RequestMapping(value="/messageNum", method = RequestMethod.GET)
   public RpcResponse<MessageNumVo> getIndexMessageNum(MessageDto messageDto) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(messageDto.getCustId(), "写手id不能为空");
      return messageApi.getIndexMessageNum(messageDto);
   }

   @ResponseBody
      @RequestMapping(value="/saveMessage", method = RequestMethod.POST)
      public RpcResponse<Boolean> saveMessage(@RequestBody WriterNoticeMessageVo writerNoticeMessageVo) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(writerNoticeMessageVo.getContent(), "消息内容不能为空");
      if (writerNoticeMessageVo.getContent().length() > 200){
         throw new IllegalArgumentException("消息长度不能超过200");
      }
      Assert.notEmpty(writerNoticeMessageVo.getReceiveWriter(), "消息接受者不能为空");
      return messageApi.saveWriterNoticeMessage(writerNoticeMessageVo);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<WriterNoticeVo>> queryMessage(WriterNoticeMessageDto writerNoticeMessageDto) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(writerNoticeMessageDto.getReceiveWriterId(), "写手id不能为空");
      return messageApi.queryWriterNoticeMessage(writerNoticeMessageDto);
   }

}

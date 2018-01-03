package com.yryz.openapi.message.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.dto.MessageDto;
import com.yryz.writer.modules.message.vo.MessageNumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}

package com.yryz.openapi.writer.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.vo.WriterVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:sun
 * @version:
 * @Description:
 * @Date:Created in 18:02 2017/11/24
 */
@Controller
@RequestMapping("services/app/v1/writer")
public class WriterController {
   @Autowired
   private WriterApi writerApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<WriterVo> detail(Long writerId) {
       return writerApi.detail(writerId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<WriterVo>> list(WriterDto writerDto) {
        return writerApi.list(writerDto);
   }

}

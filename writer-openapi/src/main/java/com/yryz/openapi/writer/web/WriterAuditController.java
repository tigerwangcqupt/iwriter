package com.yryz.openapi.writer.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.WriterAuditApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/writerAudit")
public class WriterAuditController {
	
   @Autowired
   private WriterAuditApi writerAuditApi;

   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<WriterAuditVo> detail(Long kid) {
       return writerAuditApi.detail(kid);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<WriterAuditVo>> list(WriterAuditDto writerAuditDto) {
        return writerAuditApi.list(writerAuditDto);
   }

}

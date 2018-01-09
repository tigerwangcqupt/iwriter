package com.yryz.openapi.writer.web;

import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.vo.WriterVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:sun
 * @version:
 * @Description:
 * @Date:Created in 18:02 2017/11/24
 */
@Controller
@RequestMapping("services/app/v1/writer")
public class WriterController extends BaseController {
	
   @Autowired
   private WriterApi writerApi;

   @ResponseBody
   @RequestMapping(value="/detail")
   public RpcResponse<WriterVo> detail(@RequestHeader String userId) {
	   return writerApi.detail(Long.valueOf(userId));
   }
   
   @ResponseBody
   @RequestMapping(value="/updateWriter")
   public RpcResponse<WriterVo> updateWriter(@RequestBody Writer writer,@RequestHeader String userId) {
	   if(StringUtils.isNotEmpty(userId)){
		   writer.setKid(Long.valueOf(userId));
		   writer.setLastUpdateUserId(userId);
	   }
       return writerApi.updateWriter(writer);
   }
   
   @ResponseBody
   @RequestMapping(value="/submitAudit")
   public RpcResponse<WriterVo> submitAudit(@RequestBody Writer writer,@RequestHeader String userId) {
	   if(StringUtils.isNotEmpty(userId)){
		   writer.setKid(Long.valueOf(userId));
		   writer.setLastUpdateUserId(userId);
	   }
       return writerApi.submitAudit(writer);
   }

   @ResponseBody
   @RequestMapping(value="/list")
   public RpcResponse<PageList<WriterVo>> list(WriterDto writerDto) {
        return writerApi.list(writerDto);
   }
   
  

}

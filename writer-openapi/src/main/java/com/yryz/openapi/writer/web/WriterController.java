package com.yryz.openapi.writer.web;

import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.vo.WriterVo;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
	public RpcResponse<WriterVo> updateWriter(@RequestBody Writer writer, @RequestHeader String userId) {
		if (StringUtils.isNotEmpty(userId)) {
			writer.setKid(Long.valueOf(userId));
			writer.setLastUpdateUserId(userId);
			if (StringUtils.isEmpty(writer.getNickName()) && StringUtils.isEmpty(writer.getHeadImg())) {
				throw new YyrzPcException(
		                   ExceptionEnum.ValidateException.getCode(),
		                   ExceptionEnum.ValidateException.getMsg(),
		                   ExceptionEnum.ValidateException.getErrorMsg());
			}
			if (StringUtils.isNotEmpty(writer.getNickName())) {
				RpcResponse<List<Writer>> result = writerApi.checkNickName(writer);
				if (result.success()) {
					if (CollectionUtils.isNotEmpty(result.getData())) {
						 throw new YyrzPcException(
				                    ExceptionEnum.NICKNAME_REPEAT_EXCEPTION.getCode(),
				                    ExceptionEnum.NICKNAME_REPEAT_EXCEPTION.getMsg(),
				                    ExceptionEnum.NICKNAME_REPEAT_EXCEPTION.getErrorMsg());
					}
				}
			}
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
	   if (StringUtils.isEmpty(writer.getUserName()) || StringUtils.isEmpty(writer.getIdentityCard())
				|| StringUtils.isEmpty(writer.getIdentityCardPhoto()) || StringUtils.isEmpty(writer.getProvice())
				|| StringUtils.isEmpty(writer.getCity()) || StringUtils.isEmpty(writer.getTel())||StringUtils.isEmpty(writer.getEmail())) {
		   throw new YyrzPcException(
                   ExceptionEnum.ValidateException.getCode(),
                   ExceptionEnum.ValidateException.getMsg(),
                   ExceptionEnum.ValidateException.getErrorMsg());
		}
       return writerApi.submitAudit(writer);
   }

   @ResponseBody
   @RequestMapping(value="/list")
   public RpcResponse<PageList<WriterVo>> list(WriterDto writerDto) {
        return writerApi.list(writerDto);
   }
}

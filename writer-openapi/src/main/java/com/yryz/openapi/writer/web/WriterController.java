package com.yryz.openapi.writer.web;

import com.yryz.common.Annotation.NotLogin;
import com.yryz.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.entity.Writer;
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
public class WriterController extends BaseController {

    @Autowired
    private WriterApi writerApi;

    @RequestMapping(value="/single", method = RequestMethod.GET)
    @ResponseBody
    @NotLogin
    public RpcResponse<Writer> queryConfig(Long id){
        return writerApi.detail(id);
    }


}

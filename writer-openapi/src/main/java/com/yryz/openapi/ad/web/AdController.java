package com.yryz.openapi.ad.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.ad.api.AdApi;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.openapi.ad.web
 * @Desc:
 * @Date: 2018/6/1.
 */
@Controller
@RequestMapping("services/app/v1/ad")
public class AdController extends BaseController {

    @Autowired
    private AdApi adApi;

    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RpcResponse<List<Ad>> list() {
        String keyWord = request.getParameter("keyWord");
        String countStr = request.getParameter("count");
        if (StringUtils.isNotEmpty(keyWord) || StringUtils.isNotEmpty(countStr)) {
            Integer count = Integer.valueOf(countStr);
            AdDto adDto = new AdDto();
            adDto.setAdKeyword(keyWord);
            adDto.setCurrentPage(1);
            adDto.setPageSize(count);
            adDto.setStatus(AdDto.adStatus.Start.getStatus());
            RpcResponse<PageList<Ad>> result = adApi.getList(adDto);
            if (result.success() && result.getData().getEntities() != null) {
                return ResponseModel.returnListSuccess(result.getData().getEntities());
            }
        }
        return ResponseModel.returnListSuccess(null);
    }
}

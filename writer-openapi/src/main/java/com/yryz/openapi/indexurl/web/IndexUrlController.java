package com.yryz.openapi.indexurl.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.openapi.index.web.IndexColumnController;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.CommonConstants;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.indexurl.IndexUrlConfigApi;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;
import com.yryz.writer.modules.indexurl.vo.IndexUrlConfigVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("services/app/v1/indexUrl")


public class IndexUrlController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexColumnController.class);

    @Autowired
    private IndexUrlConfigApi indexUrlConfigApi;


    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public RpcResponse<Map<String,Object>> list() {
        Map<String,Object> map = new HashMap<>();
        IndexUrlConfigDto indexUrlConfigDto = new IndexUrlConfigDto();
        indexUrlConfigDto.setShelveFlag(CommonConstants.SHELVE_YES);
        RpcResponse<PageList<IndexUrlConfigVo>> result = indexUrlConfigApi.list(indexUrlConfigDto);
        if(result.success()){
            PageList<IndexUrlConfigVo> pageList = result.getData();
            if(null != pageList && CollectionUtils.isNotEmpty(pageList.getEntities())){
                List<IndexUrlConfigVo> list = pageList.getEntities();
                if(CollectionUtils.isNotEmpty(list)){
                    map.put("data",list.get(0).getConfigHref());
                    return ResponseModel.returnListSuccess(map);
                }
            }
        }
        return ResponseModel.returnListSuccess(map);
    }
}

package com.yryz.openapi.index.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.indexcolumn.IndexColumnApi;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("services/app/v1/indexColumn")
public class IndexColumnController {
   @Autowired
   private IndexColumnApi indexColumnApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<IndexColumnVo> detail(Long indexColumnId) {
       return indexColumnApi.detail(indexColumnId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<List<IndexColumnVo>> list(IndexColumnDto indexColumnDto) {
//        return indexColumnApi.list(indexColumnDto);

      return indexColumnApi.listByWriter(indexColumnDto);
   }

}

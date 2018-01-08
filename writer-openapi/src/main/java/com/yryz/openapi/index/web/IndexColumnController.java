package com.yryz.openapi.index.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.indexcolumn.IndexColumnApi;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.message.vo.IndexTipsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("services/app/v1/indexColumn")
public class IndexColumnController {

   private static final Logger logger = LoggerFactory.getLogger(IndexColumnController.class);

   @Autowired
   private IndexColumnApi indexColumnApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<IndexColumnVo> detail(Long indexColumnId) {
       return indexColumnApi.detail(indexColumnId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<IndexColumnVo> list(IndexColumnDto indexColumnDto, @RequestHeader Long userId) {
      Assert.notNull(userId, "写手id不能为空");
      logger.info("------进入首页栏目controller-----");
      indexColumnDto.setCustId(userId);
//        return indexColumnApi.list(indexColumnDto);
      return indexColumnApi.listByWriter(indexColumnDto);
   }

   @ResponseBody
   @RequestMapping(value="/getIndexTips", method = RequestMethod.GET)
   public RpcResponse<List<IndexTipsVo>> getIndexTips(IndexColumnDto indexColumnDto, @RequestHeader Long userId) {
//        return indexColumnApi.list(indexColumnDto);
//      Assert.notNull(indexColumnDto.getCustId(), "写手id不能为空");
      Assert.notNull(userId, "写手id不能为空");
      indexColumnDto.setCustId(userId);
      return indexColumnApi.getIndexTips(indexColumnDto);
   }

}

package com.yryz.writer.modules.writer.service;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterAdminVo;
import com.yryz.writer.modules.writer.vo.WriterModelVo;
import com.yryz.writer.modules.writer.vo.WriterVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * 
  * @ClassName: WriterService
  * @Description: Writer业务访问接口
  * @author liuyanjun
  * @date 2018-01-03 15:03:10
  *
 */
@Repository
public interface WriterService extends BaseService {

   PageList<WriterVo> selectList(WriterDto writerDto);
   
   List<Writer> checkNickName(Writer writer);
   
   PageList<WriterAdminVo> selectWriterList(WriterDto writerDto);
   
   List<WriterAdminVo> selectWriterExportList(WriterDto writerDto);
   
   WriterAdminVo selectWriterDetail(Long kid);

   WriterVo detail(Long writerId);
   
   Integer updateWriter(WriterAdminVo writerAdminVo);

   PageList<WriterAdminVo> selectListAdmin(WriterDto writerDto);

   Writer selectByPhone(String phone);

   String getImageCode (String phone);

   Boolean checkImageCode(String phone, String imageCode);

   WriterModelVo selectWriterByParameters(WriterDto writerDto);

   /**
    * 后台查询写手收益列表
    * @param writerDto
    * @return
    */
   PageList<WriterAdminRefProfit> selectAdminProfitList(WriterDto writerDto);

   /**
    * 后台查询写手收益列表
    * @param writerDto
    * @return
    */
   List<WriterAdminRefProfit> selectAllAdminProfitList(WriterDto writerDto);

   String getUserToken(String custId);

   Integer deleteUserToken(String custId);

   String addUserToken(String custId);

   Integer updateWriterProfit(Writer writer);

}
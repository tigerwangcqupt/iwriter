package com.yryz.writer.modules.writer;

import java.util.List;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;

/**
 * 
 * @ClassName: WriterAuditApi
 * @Description: WriterAuditApi接口
 * @author liuyanjun
 * @date 2018-01-04 09:51:21
 *
 */
public interface WriterAuditApi {

	/**
	*  获取WriterAudit明细
	*  @param  id
	*  @return
	* */
	RpcResponse<WriterAudit> get(Long id);

    /**
    *  获取WriterAudit明细
    *  @param  id
    *  @return
    * */
    RpcResponse<WriterAuditVo> detail(Long kid);

    /**
    * 获取WriterAudit列表
    * @param writerAuditDto
    * @return
    * */
    RpcResponse<PageList<WriterAuditVo>> list(WriterAuditDto writerAuditDto);
    
    RpcResponse<List<WriterAuditVo>> exportList(WriterAuditDto writerAuditDto);
    
    RpcResponse<Integer> audit(WriterAuditVo WriterAuditVo);
    

}

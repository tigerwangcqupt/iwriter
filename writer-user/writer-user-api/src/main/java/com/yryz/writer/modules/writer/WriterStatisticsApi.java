package com.yryz.writer.modules.writer;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.vo.WriterStatisticsVo;
import com.yryz.writer.modules.writer.dto.WriterStatisticsDto;
import com.yryz.writer.modules.writer.entity.WriterStatistics;

/**
 * 
 * @ClassName: WriterStatisticsApi
 * @Description: WriterStatisticsApi接口
 * @author liuyanjun
 * @date 2018-02-02 14:15:21
 *
 */
public interface WriterStatisticsApi {

	/**
	*  获取WriterStatistics明细
	*  @param  id
	*  @return
	* */
	RpcResponse<WriterStatistics> get(Long kid);

    /**
    *  获取WriterStatistics明细
    *  @param  id
    *  @return
    * */
    RpcResponse<WriterStatisticsVo> detail(Long kid);

    /**
    * 获取WriterStatistics列表
    * @param writerStatisticsDto
    * @return
    * */
    RpcResponse<PageList<WriterStatisticsVo>> list(WriterStatisticsDto writerStatisticsDto);
    
    RpcResponse<WriterStatistics> insert(WriterStatistics writerStatistics);
    
    RpcResponse<WriterStatistics> update(WriterStatistics writerStatistics);
    
    

}

package com.yryz.writer.modules.writer.service.impl;

import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.vo.NoticeReceiveWriter;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageVo;
import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.writer.WriterStatisticsApi;
import com.yryz.writer.modules.writer.dao.persistence.WriterAuditDao;
import com.yryz.writer.modules.writer.dao.persistence.WriterDao;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WriterAuditServiceImpl extends BaseServiceImpl implements WriterAuditService {
	
	private static final Logger logger = LoggerFactory.getLogger(WriterAuditServiceImpl.class);

    @Autowired
    private WriterAuditDao writerAuditDao;
    
    @Autowired
    private WriterDao writerDao;
    
    @Autowired
    private ProfitApi profitApi;
    
    @Autowired
    private MessageApi messageApi;
    
    @Autowired
    private WriterStatisticsApi writerStatisticsApi;

    protected BaseDao getDao() {
        return writerAuditDao;
    }

    public PageList<WriterAuditVo> selectList(WriterAuditDto writerAuditDto){
        PageUtils.startPage(writerAuditDto.getCurrentPage(), writerAuditDto.getPageSize());
        List<WriterAudit> list = writerAuditDao.selectList(writerAuditDto);
        List<WriterAuditVo> writerAuditVoList = new ArrayList <WriterAuditVo>();
        if(CollectionUtils.isNotEmpty(list)){
            for(WriterAudit writerAudit : list){
                WriterAuditVo writerAuditVo = new WriterAuditVo();
                BeanUtils.copyProperties(writerAudit, writerAuditVo);
                writerAuditVoList.add(writerAuditVo);
            }
        }
        return new PageModel<WriterAuditVo>().getPageList(writerAuditVoList);
    }
    
    public List<WriterAuditVo> exportList(WriterAuditDto writerAuditDto){
        List<WriterAudit> list = writerAuditDao.selectList(writerAuditDto);
        List<WriterAuditVo> writerAuditVoList = new ArrayList <WriterAuditVo>();
        if(CollectionUtils.isNotEmpty(list)){
            for(WriterAudit writerAudit : list){
                WriterAuditVo writerAuditVo = new WriterAuditVo();
                BeanUtils.copyProperties(writerAudit, writerAuditVo);
                writerAuditVoList.add(writerAuditVo);
            }
        }
        return writerAuditVoList;
    }

    public WriterAuditVo detail(Long kid) {
        WriterAudit writerAudit = writerAuditDao.selectByKid(WriterAudit.class,kid);
        
        WriterAuditVo writerAuditVo = new WriterAuditVo();
        if (writerAudit != null) {
            BeanUtils.copyProperties(writerAudit, writerAuditVo);
        }
        return writerAuditVo;
    }

	@Override
	@Transactional
	public int audit(WriterAuditVo writerAuditVo) {
		WriterAudit writerAudit = new WriterAudit();
		BeanUtils.copyProperties(writerAuditVo, writerAudit);
		if (writerAudit != null) {
			int auditStatus = writerAuditVo.getAuditStatus().intValue();
			// 更新写手审核表
			writerAudit.setAuditDate(new Date());
			writerAudit.setLastUpdateDate(new Date());
			writerAuditDao.update(writerAudit);
			// 更新写手表状态
			Writer writer = new Writer();
			writer.setKid(writerAudit.getWriterKid());
			writer.setUserStatus(writerAudit.getAuditStatus());
			writer.setLastUpdateDate(new Date());
			writer.setLastUpdateUserId(writerAuditVo.getLastUpdateUserId());
			if(auditStatus==2){
				writer.setRemark(writerAudit.getRemark());
			}
			writerDao.update(writer);

			if(auditStatus==2){
				// 审核通过，绑定资金主体
				Writer writer1 = new Writer();
				BeanUtils.copyProperties(writerAuditVo, writer1);
				writer1.setKid(writerAuditVo.getWriterKid());
				RpcResponse<Writer> profitResult = profitApi.bindCapital(writer1);
				if (!profitResult.success()) {
					logger.error("profitApi bindCapital：绑定资金主体失败");
					throw new YyrzPcException(ExceptionEnum.ADD_OWNER_EXCEPTION.getCode(),ExceptionEnum.ADD_OWNER_EXCEPTION.getMsg(),
		                    ExceptionEnum.ADD_OWNER_EXCEPTION.getErrorMsg());
				}
				// 审核通过，发送通知消息
				WriterNoticeMessageVo writerNoticeMessageVo = new WriterNoticeMessageVo();
				writerNoticeMessageVo.setContent("恭喜您！您已成功通过悠然一指写手入驻资料申请的审核，您现在可以通过领取平台任务或自由投稿赚取稿费啦！");
				writerNoticeMessageVo.setTriggerType(1);
				writerNoticeMessageVo.setSendUserId(Long.valueOf(writerAuditVo.getLastUpdateUserId()));
				NoticeReceiveWriter noticeReceiveWriter = new NoticeReceiveWriter();
				noticeReceiveWriter.setKid(writerAuditVo.getWriterKid());
				noticeReceiveWriter.setUserNickName(writerAuditVo.getUserName());
				List<NoticeReceiveWriter> receiveWriter = new ArrayList<NoticeReceiveWriter>();
				receiveWriter.add(noticeReceiveWriter);
				writerNoticeMessageVo.setReceiveWriter(receiveWriter);
				RpcResponse<Boolean> msgResult = messageApi.saveWriterNoticeMessage(writerNoticeMessageVo);
				if (!msgResult.success()) {
					logger.error("messageApi saveWriterNoticeMessage 消息接口调用失败");
					throw new YyrzPcException(ExceptionEnum.AUDITPASS_SENDMSG_EXCEPTION.getCode(),ExceptionEnum.AUDITPASS_SENDMSG_EXCEPTION.getMsg(),
		                    ExceptionEnum.AUDITPASS_SENDMSG_EXCEPTION.getErrorMsg());
				}
				
				//写手审核通过初始化统计信息
				WriterStatistics writerStatistics = new WriterStatistics();
				writerStatistics.setWriterKid(writerAuditVo.getWriterKid());
				RpcResponse<WriterStatistics> rst = writerStatisticsApi.insert(writerStatistics);
				if(!rst.success()){
					logger.error("写手审核通过初始化统计信息接口调用失败");
					throw new YyrzPcException(ExceptionEnum.Exception.getCode(),ExceptionEnum.Exception.getMsg(),
		                    ExceptionEnum.Exception.getErrorMsg());
				}
				
			}
			
		}
		return 1;
	}
    
    
    
    
 }

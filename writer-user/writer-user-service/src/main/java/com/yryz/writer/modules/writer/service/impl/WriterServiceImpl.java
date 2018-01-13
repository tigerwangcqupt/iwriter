package com.yryz.writer.modules.writer.service.impl;

import com.yryz.service.api.api.exception.RedisOptException;
import com.yryz.service.api.basic.api.SmsAPI;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.redis.utils.JedisUtils;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterAdminVo;
import com.yryz.writer.modules.writer.vo.WriterCapitalVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.writer.vo.WriterVo;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.dao.persistence.WriterAuditDao;
import com.yryz.writer.modules.writer.dao.persistence.WriterDao;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.service.WriterService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class WriterServiceImpl extends BaseServiceImpl implements WriterService {

    public static final Logger LOGGER = LoggerFactory.getLogger(WriterServiceImpl.class);

    @Autowired
    private WriterDao writerDao;

    @Autowired
    private SmsAPI smsAPI;

    @Autowired
    private WriterAuditDao writerAuditDao;

    public final static int LOCK_EXPIRE_DEFAULT = 60;


    public static final String MESSAGE = "yryz.writer.token";

    public static String getMessagekey(String custId){
        return MESSAGE +"."+ custId;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected BaseDao getDao() {
        return writerDao;
    }

    public PageList<WriterVo> selectList(WriterDto writerDto){
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        List<Writer> list = writerDao.selectList(writerDto);
        List<WriterVo> writerVoList = new ArrayList <WriterVo>();
        if(list != null && list.size() > 0) {
            for(Writer writer : list){
                WriterVo writerVo = new WriterVo();
                BeanUtils.copyProperties(writer, writerVo);
                writerVoList.add(writerVo);
            }
        }
        return new PageModel<WriterVo>().getPageList(writerVoList);
    }
    
    public PageList<WriterAdminVo> selectWriterList(WriterDto writerDto){
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        List<WriterAdminVo> list = writerDao.selectWriterList(writerDto);
        return new PageModel<WriterAdminVo>().getPageList(list);
    }
    
    public List<Writer> checkNickName(Writer writer){
    	return writerDao.checkNickName(writer);
    }
    
    public List<WriterAdminVo> selectWriterExportList(WriterDto writerDto){
        return writerDao.selectWriterList(writerDto);
    }

    public WriterAdminVo selectWriterDetail(Long kid){
    	 return writerDao.selectWriterDetail(kid);
    }

    public WriterVo detail(Long kid) {
        Writer writer = writerDao.selectByKid(Writer.class,kid);
        //查询最新一条审核信息
        WriterAudit writerAudit = writerAuditDao.selectAuditDetail(kid);
        WriterVo writerVo = new WriterVo();
        if (writer != null) {
        	BeanUtils.copyProperties(writer, writerVo);
        	if(writerAudit !=null){
        		writerVo.setRemark(writerAudit.getRemark());        		
        	}
        }
        return writerVo;
    }
    
    @Override
    public Integer updateWriter(WriterAdminVo writerAdminVo) {
    	return writerDao.updateWriter(writerAdminVo);
    }

    @Override
    public PageList<WriterAdminVo> selectListAdmin(WriterDto writerDto) {
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        List<Writer> list = writerDao.selectAdminList(writerDto);
        List<WriterAdminVo> writerVoList = new ArrayList <WriterAdminVo>();
        if(list != null && list.size() > 0) {
            for(Writer writer : list){
                WriterAdminVo writerVo = new WriterAdminVo();
                BeanUtils.copyProperties(writer,writerVo);
                writerVoList.add(writerVo);
            }
        }
        return new PageModel<WriterAdminVo>().getPageList(writerVoList);
    }

    @Override
    public Writer selectByPhone(String phone) {
        Writer writer = writerDao.selectByPhone(phone);
        return writer;
    }

    @Override
    public String getImageCode(String phone) {
        String code = null;
        try {
            code = smsAPI.getSmsImgCode(phone);
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw YyrzPcException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw YyrzPcException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
        return code;
    }

    @Override
    public Boolean checkImageCode(String phone,String imageCode) {
        Boolean codeFlag = null;
        try {
            codeFlag = smsAPI.checkSmsImgCode(phone,imageCode);
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw YyrzPcException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw YyrzPcException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
        return codeFlag;
    }

    @Override
    public String getUserToken(String custId){
        String token = this.getToken(custId);
        return token;
    }

    @Override
    public String addUserToken(String custId){
        String token="";
        String tokenValue = UUID.randomUUID().toString().replaceAll("-", "");
        if(this.addToken(custId,tokenValue)){
            return tokenValue;
        }
        return null;
    }

    @Override
    public Integer updateWriterProfit(Writer writer) {
        return writerDao.updateWriterProfit(writer);
    }

    @Override
    public Integer deleteUserToken(String custId){
        Long count = 0l;
        String key = getMessagekey(custId);
        try {
             count = JedisUtils.del(key);
        } catch (Exception e) {
            logger.error("[deleteUserToken] error", e);
            throw new RedisOptException(e);
        }

        return  count.intValue();
    }


    /**
     * 查询用户的token
     */
    public String getToken(String custId){
        String key = getMessagekey(custId);
        try {
            String token = JedisUtils.get(key);
            return token;
        } catch (Exception e) {
            logger.error("[getToken] error", e);
            throw new RedisOptException(e);
        }
    }


    /**
     * 添加用户token
     * @param custId
     * @param tokenValue
     */
    public boolean addToken(String custId , String tokenValue){
        String key = getMessagekey(custId);
        String lockKey = key + "_LOCK";

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);

        try {
            long lock = JedisUtils.setnx(lockKey, "LOCK",0);
            if (lock > 0) {
                if (0 == JedisUtils.expire(lockKey, LOCK_EXPIRE_DEFAULT)) {// 设置失效时间失败，则删除lock，抛出异常
                    JedisUtils.del(lockKey);
                    throw new RedisOptException("addUnread's lock setting fault");
                }
                JedisUtils.set(key, tokenValue,0 );
                JedisUtils.del(lockKey);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JedisUtils.del(lockKey);
            logger.error("[addToken] error", e);
            throw new RedisOptException(e);
        }
    }


    @Override
    public WriterCapitalVo selectWriterByParameters(WriterDto writerDto) {
        return writerDao.selectWriterByParameters(writerDto);
    }

    @Override
    public PageList<WriterAdminRefProfit> selectAdminProfitList(WriterDto writerDto) {
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        return new PageModel<WriterAdminRefProfit>().getPageList(writerDao.selectAdminProfitList(writerDto));
    }

    @Override
    public List<WriterAdminRefProfit> selectAllAdminProfitList(WriterDto writerDto) {
        return writerDao.selectAdminProfitList(writerDto);
    }
}

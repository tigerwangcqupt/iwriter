package com.yryz.writer.modules.fans.service.impl;

import com.yryz.common.redis.utils.JedisUtils;
import com.yryz.common.redis.utils.StringUtils;
import com.yryz.common.utils.DateUtil;
import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.fans.vo.FansVo;
import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.dao.persistence.FansDao;
import com.yryz.writer.modules.fans.service.FansService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FansServiceImpl extends BaseServiceImpl implements FansService {

    @Autowired
    private FansDao fansDao;
    @Autowired
    private MessageApi messageApi;

    protected BaseDao getDao() {
        return fansDao;
    }

    //获取全部粉丝个数
    public int selectCount(Long userId) {
        return fansDao.selectCount(userId);
    }

    //获取新增粉丝个数
    public int selectNewFansCount(Long userId) {
        //从缓存中拿到上次进入的时间,拼接成redis的key
        String writerId = String.valueOf(userId);
        String nextDate = JedisUtils.get("nextFansFlag" + writerId);
        if (StringUtils.isBlank(nextDate)) {
            nextDate = DateUtil.getString(new Date());
        }
        //清空上次时间,并记录本次时间
        JedisUtils.del("nextFansFlag" + writerId);
        JedisUtils.del("preFansFlag" + writerId);
        JedisUtils.set("preFansFlag" + writerId, nextDate, 0);
        JedisUtils.set("nextFansFlag" + writerId, DateUtil.getString(new Date()), 0);

        //清空粉丝数量缓存
        messageApi.cleanMessageTips(ModuleEnum.FANS, userId);

        FansDto fansDto = new FansDto();
        fansDto.setWriterId(userId);
        fansDto.setCreateDate(nextDate);
        return fansDao.selectNewFansCount(fansDto);
    }


    //获取全部粉丝列表
    public PageList<FansVo> selectList(FansDto fansDto) {
        PageUtils.startPage(fansDto.getCurrentPage(), fansDto.getPageSize());
        List<Fans> list = fansDao.selectList(fansDto);
        List<FansVo> fansVoList = getUserInfo(list);
        return new PageModel<FansVo>().getPageList(fansVoList);
    }

    //获取新粉丝列表
    public PageList<FansVo> selectNewFans(FansDto fansDto) {
        String writerId = String.valueOf(fansDto.getWriterId());
        String preDate = JedisUtils.get("preFansFlag" + writerId);
        if (StringUtils.isNotBlank(preDate)) {
            fansDto.setCreateDate(preDate);
        }
        PageUtils.startPage(fansDto.getCurrentPage(), fansDto.getPageSize());
        List<Fans> list = fansDao.selectList(fansDto);
        List<FansVo> fansVoList = getUserInfo(list);
        return new PageModel<FansVo>().getPageList(fansVoList);
    }


    public FansVo detail(Long fansId) {
        Fans fans = fansDao.selectByKid(Fans.class, fansId);
        FansVo fansVo = new FansVo();
        if (fansVo != null) {
            //Fans to FansVo
        }
        return fansVo;
    }

    private List<FansVo> getUserInfo(List<Fans> list) {
        List<FansVo> fansVoList = new ArrayList<FansVo>();
        if (list != null && list.size() > 0) {
            for (Fans fans : list) {
                FansVo fansVo = new FansVo();
                //Fans to FansVo
                Long userId = fans.getUserId();
                fansVo.setUserId(userId);
                //获取用户信息
                FansVo user = fansDao.selectUserById(userId);
                fansVo.setNickName(user.getNickName());
                fansVo.setHeadImg(user.getHeadImg());
                fansVoList.add(fansVo);
            }
        }
        return fansVoList;
    }
}

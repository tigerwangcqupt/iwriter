package com.yryz.writer.modules.fans.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.fans.vo.FansVo;
import com.yryz.writer.modules.fans.entity.Fans;
import com.yryz.writer.modules.fans.dto.FansDto;
import com.yryz.writer.modules.fans.dao.persistence.FansDao;
import com.yryz.writer.modules.fans.service.FansService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;


@Service
public class FansServiceImpl extends BaseServiceImpl implements FansService {

    @Autowired
    private FansDao fansDao;

    protected BaseDao getDao() {
        return fansDao;
    }

    public PageList<FansVo> selectList(FansDto fansDto){
        Jedis jedis=new Jedis();

        PageUtils.startPage(fansDto.getCurrentPage(), fansDto.getPageSize());
        List<Fans> list = fansDao.selectList(fansDto);
        List<FansVo> fansVoList = new ArrayList <FansVo>();
        if(list != null && list.size() > 0) {
            for(Fans fans : list){
                FansVo fansVo = new FansVo();
                //Fans to FansVo
                Long userId = fans.getUserId();
                fansVo.setUserId(userId);
                //获取用户信息
                FansVo user = fansDao.selectUserById(userId);
                fansVo.setNickName(user.getNickName());
                fansVo.setHeadImg(user.getHeadImg());
                //判断新老粉丝
                // TODO: 2018/1/4
                fansVoList.add(fansVo);
            }
        }
        return new PageModel<FansVo>().getPageList(fansVoList);
    }


    public FansVo detail(Long fansId) {
        Fans fans = fansDao.selectByKid(Fans.class,fansId);
        FansVo fansVo = new FansVo();
        if (fansVo != null) {
            //Fans to FansVo
        }
        return fansVo;
    }
 }

package com.yryz.writer.modules.writer.provider;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.constant.IDCardValidate;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.WriterAuditApi;
import com.yryz.writer.modules.writer.vo.*;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import com.yryz.writer.modules.writer.service.WriterService;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterProvider implements WriterApi {

	private static final Logger logger = LoggerFactory.getLogger(WriterProvider.class);

	@Autowired
	private WriterService writerService;
	
	@Autowired
	private IdAPI idAPI;
	
	@Autowired
	private WriterAuditService writerAuditService;
	
	@Autowired
	private ProvinceApi provinceApi;
	
	@Autowired
	private CityApi cityApi;

	@Autowired
	private BankApi bankApi;

	@Autowired
	private WriterAuditApi writerAuditApi;
	
	/**
	*  获取Writer明细
	*  @param  writerId
	*  @return
	* */
	public RpcResponse<Writer> get(Long writerId) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.get(Writer.class, writerId));
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Writer明细
	*  @param  kid
	*  @return
	* */
	public RpcResponse<WriterVo> detail(Long kid) {
		try {
			WriterVo writerVo = writerService.detail(kid);
			if(writerVo!=null){
				ProvinceVo provinceVo = provinceApi.selectProvinces(writerVo.getProvice()).getData();
				if(provinceVo!=null){
					writerVo.setProviceName(provinceVo.getProvinceName());
				}
				CityVo cityVo = cityApi.selectCity(writerVo.getCity()).getData();
				if(cityVo!=null){
					writerVo.setCityName(cityVo.getCityName());
				}

				//设置手持照片
				BankDto bankDto = new BankDto();
				bankDto.setCreateUserId(String.valueOf(kid));
				BankVo bankVo = bankApi.selectByParameters(bankDto).getData();
				if(bankVo != null){
					writerVo.setHandheldPhoto(bankVo.getHandheldPhoto());
					writerVo.setUserName(bankVo.getUserName());
					writerVo.setIdentityCard(bankVo.getUserCart());
					writerVo.setProvice(bankVo.getProvice());
					writerVo.setProviceName(bankVo.getProviceName());
					writerVo.setCity(bankVo.getCity());
					writerVo.setCityName(bankVo.getCityName());
					writerVo.setTel(writerVo.getPhone());
					writerVo.setEmail(writerVo.getEmail());
				}

			}
			return ResponseModel.returnObjectSuccess(writerVo);
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
	}
	
	/**
	*  获取Writer明细
	*  @param  writerId
	*  @return
	* */
	public RpcResponse<WriterAdminVo> getWriterDetail(Long kid) {
		try {
			WriterAdminVo writerAdminVo = writerService.selectWriterDetail(kid);
			if(writerAdminVo!=null){
				ProvinceVo provinceVo = provinceApi.selectProvinces(writerAdminVo.getProvice()).getData();
				if(provinceVo!=null){
					writerAdminVo.setProviceName(provinceVo.getProvinceName());
				}
				CityVo cityVo = cityApi.selectCity(writerAdminVo.getCity()).getData();
				if(cityVo!=null){
					writerAdminVo.setCityName(cityVo.getCityName());
				}

				//设置手持照片
				BankDto bankDto = new BankDto();
				bankDto.setCreateUserId(String.valueOf(kid));
				BankVo bankVo = bankApi.selectByParameters(bankDto).getData();
				if(bankVo != null){
					writerAdminVo.setHandheldPhoto(bankVo.getHandheldPhoto());
					writerAdminVo.setUserName(bankVo.getUserName());
				}
			}
			return ResponseModel.returnObjectSuccess(writerAdminVo);
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

    /**
    * 获取Writer列表
    * @param writerDto
    * @return
    *
	*/
    public RpcResponse<PageList<WriterVo>> list(WriterDto writerDto) {
        try {
			 return ResponseModel.returnListSuccess(writerService.selectList(writerDto));
        } catch (Exception e) {
        	logger.error("获取Writer列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }
    
    public RpcResponse<PageList<WriterAdminVo>> writerList(WriterDto writerDto) {
        try {
			 return ResponseModel.returnListSuccess(writerService.selectWriterList(writerDto));
        } catch (Exception e) {
        	logger.error("获取Writer列表失败", e);
       		return ResponseModel.returnException(e);
        }
    }
    
    public RpcResponse<List<WriterAdminVo>> writerExportList(WriterDto writerDto) {
        try {
			 return ResponseModel.returnListSuccess(writerService.selectWriterExportList(writerDto));
        } catch (Exception e) {
        	logger.error("获取Writer列表失败", e);
       		return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<WriterVo> updateWriter(Writer writer) {
		 try {
			 writerService.update(writer);
			 WriterVo writerVo = writerService.detail(writer.getKid());
			 return ResponseModel.returnObjectSuccess(writerVo);
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}
	
	@Override
	public RpcResponse<Integer> updateWriterRemark(WriterAdminVo writerAdminVo) {
		 try {
			 return ResponseModel.returnObjectSuccess(writerService.updateWriter(writerAdminVo));
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}

	@Override
	public RpcResponse<WriterVo> submitAudit(Writer writer) {
		 try {
			 return ResponseModel.returnObjectSuccess(writerService.submitAudit(writer));
        } catch (Exception e) {
        	 logger.error("更新Writer信息失败", e);
       		 return ResponseModel.returnException(e);
        }
	}
	
	@Override
	public RpcResponse<PageList<WriterAdminVo>> listAdmin(WriterDto writerDto) {
		try {
			return ResponseModel.returnListSuccess(writerService.selectListAdmin(writerDto));
		} catch (Exception e) {
			logger.error("获取Writer列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<PageList<WriterAdminRefProfit>> selectAdminProfitList(WriterDto writerDto) {
		try {
			return ResponseModel.returnListSuccess(writerService.selectAdminProfitList(writerDto));
		} catch (Exception e) {
			logger.error("获取Writer列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public List<WriterAdminRefProfit> selectAllAdminProfitList(WriterDto writerDto) {
		return writerService.selectAllAdminProfitList(writerDto);
	}

	@Override
	public RpcResponse<WriterCapitalVo> selectWriterByParameters(WriterDto writerDto) {
		try {
			return ResponseModel.returnListSuccess(writerService.selectWriterByParameters(writerDto));
		} catch (Exception e) {
			logger.error("获取Writer收益失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Long> register(Writer user) {
		try {
			/*//idCard：CR+9位 需在数据库给出9位初始值
			Long suffix_id = idAPI.getId(CR);
			user.setIdCard(CR + suffix_id);*/
			Long kid = idAPI.getId("yryz_writer");
			user.setKid(kid);
			writerService.insertByPrimaryKeySelective(user);


			//设置为写手审核成功
			writerAuditApi.auditUser(user);


			return ResponseModel.returnObjectSuccess(kid);
		} catch (Exception e) {
			logger.error("新增用户失败！" + e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Integer> updateByPrimaryKeySelective(Writer user) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.update(user));
		} catch (Exception e) {
			logger.error("修改用户失败！" + e);
			return ResponseModel.returnException(e);
		}
	}

	
	/**
	 *  获取Writer明细
	 *  @param  phone
	 *  @return
	 * */
	public RpcResponse<Writer> selectByPhone(String phone) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.selectByPhone(phone));
		} catch (Exception e) {
			logger.error("获取Writer明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
	 *  获取图形验证码
	 *  @param  phone
	 *  @return
	 * */
	public RpcResponse<String> getImageCode(String phone) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.getImageCode(phone));
		} catch (Exception e) {
			logger.error("获取图形验证码失败", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
	 *  检查图形验证码
	 *  @param  phone
	 *  @param  imageCode
	 *  @return
	 * */
	public RpcResponse<Boolean> checkImageCode(String phone,String imageCode){
		try {
			return ResponseModel.returnObjectSuccess(writerService.checkImageCode(phone,imageCode));
		} catch (Exception e) {
			logger.error(" 检查图形验证码失败", e);
			return ResponseModel.returnException(e);
		}
	}



	/**
	 * 获取用户token
	 * @param  custId
	 * @return
	 */
	public RpcResponse<String> getUserToken(String custId) {
		try {
			return ResponseModel.returnObjectSuccess(writerService.getUserToken(custId));
		} catch (Exception e) {
			logger.error("获取用户token失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Integer> deleteUserToken(String custId){
		try {
			return ResponseModel.returnObjectSuccess(writerService.deleteUserToken(custId));
		} catch (Exception e) {
			logger.error("删除用户token失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<String> addUserToken(String custId){
		try {
			return ResponseModel.returnObjectSuccess(writerService.addUserToken(custId));
		} catch (Exception e) {
			logger.error("添加用户token失败", e);
			return ResponseModel.returnException(e);
		}
	}


	@Override
	public RpcResponse<String> addUserPhoneVeriCode(String custId,String veriCode){
		try {
			return ResponseModel.returnObjectSuccess(writerService.addUserPhoneVeriCode(custId,veriCode));
		} catch (Exception e) {
			logger.error("添加用户手机验证码失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<String> getUserPhoneVeriCode(String custId){
		try {
			return ResponseModel.returnObjectSuccess(writerService.getUserPhoneVeriCode(custId));
		} catch (Exception e) {
			logger.error("获取用户验证码失败", e);
			return ResponseModel.returnException(e);
		}
	}



	@Override
	public RpcResponse<List<Writer>> checkNickName(Writer writer) {
		try {
			return ResponseModel.returnListSuccess(writerService.checkNickName(writer));
		} catch (Exception e) {
			logger.error("昵称重复，校验失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

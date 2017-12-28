package com.yryz.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yryz.common.exception.BaseException;


public class ReturnModel {
	/**
	 * bean to result
	 * 
	 * @param object
	 * @return
	 * @throws BaseException
	 */
	public static ReturnCode beanToString(Object object) throws BaseException {
		ReturnCode result = new ReturnCode(ReturnCode.SUCCESS, ReturnCode.SUCCESSMSG);

		try {
			if (object != null) {
				result.setData(object);
			}
			if(object==null) {
                result.setData(new HashMap<>());
            }
		} catch (Exception e) {
			throw new BaseException(ReturnCode.ERROR);
		}

		return result;
	}

	/**
	 * List to result
	 * 
	 * @param object
	 * @return
	 * @throws BaseException
	 */
	public static ReturnCode listToString(List<? extends Object> list) throws BaseException {
		ReturnCode result = new ReturnCode();

		try {
			if (list != null) {
				result.setData(list);
				result.setRet(ReturnCode.SUCCESS);
			} else {
				list = new ArrayList<Object>();
				result.setData(list);
				result.setRet(ReturnCode.SUCCESS);
			}
		} catch (Exception e) {
			throw new BaseException(ReturnCode.ERROR);
		}

		return result;
	}

	/**
	 * string to result
	 * 
	 * @param msg
	 * @return
	 */
	public static ReturnCode returnSuccess(String msg) {
		ReturnCode result = new ReturnCode();
		result.setMsg(msg);
		result.setRet(ReturnCode.SUCCESS);
		return result;
	}

	/**
	 * Exception to result
	 * 
	 * @param e
	 * @return
	 */
	public static ReturnCode returnException(BaseException e) {
		ReturnCode result = new ReturnCode();
		result.setMsg(e.getMsg());
		result.setRet(e.getCode());
		return result;
	}

	public static ReturnCode returnException() {
		ReturnCode result = new ReturnCode();
		result.setMsg("sys error");
		result.setRet(ReturnCode.ERROR);
		return result;
	}

	public static ReturnCode returnException(int code) {
		ReturnCode result = new ReturnCode();
		result.setMsg("sys error");
		result.setRet(code);
		return result;
	}

	public static ReturnCode returnException(String msg) {
		ReturnCode result = new ReturnCode();
		result.setMsg(msg);
		result.setRet(ReturnCode.ERROR);
		return result;
	}

	public static ReturnCode returnException(int code, String msg) {
		ReturnCode result = new ReturnCode();
		result.setMsg(msg);
		result.setRet(code);
		return result;
	}

}

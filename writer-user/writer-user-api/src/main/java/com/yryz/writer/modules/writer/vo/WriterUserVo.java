package com.yryz.writer.modules.writer.vo;

import com.yryz.writer.modules.platform.vo.AuthInfo;

import java.io.Serializable;

/**
 * 
  * @ClassName: Writer
  * @Description: 写手信息表实体类
  * @author liuyanjun
  * @date 2018-01-03 10:39:54
  *
 */
public class WriterUserVo extends WriterVo  implements Serializable {


    /**
     * 用户ID
	 */
	private String userId;

	/**
	 * 用户token
	 */
	private String token;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
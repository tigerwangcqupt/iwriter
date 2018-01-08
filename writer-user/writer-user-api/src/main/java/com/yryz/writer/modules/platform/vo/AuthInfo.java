package com.yryz.writer.modules.platform.vo;

import java.io.Serializable;

/**
 * Token信息
 * 
 * @author Pxie
 *
 */
@SuppressWarnings("serial")
public class AuthInfo implements Serializable {

	private String custId;
	private String token;
	private long expireAt;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(long expireAt) {
		this.expireAt = expireAt;
	}

}

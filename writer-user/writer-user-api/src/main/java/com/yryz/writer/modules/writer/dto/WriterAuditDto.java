package com.yryz.writer.modules.writer.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: WriterAuditDto
 * @Description: WriterAuditDto
 * @author liuyanjun
 * @date 2018-01-04 09:51:21
 *
 */
public class WriterAuditDto extends PageList {
	
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}

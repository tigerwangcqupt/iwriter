package com.yryz.writer.modules.writer.dto;

import com.yryz.writer.modules.writer.entity.WriterAudit;

/**
 * @ClassName: WriterAuditDto
 * @Description: WriterAuditDto
 * @author liuyanjun
 * @date 2018-01-03 11:25:37
 *
 */
public class WriterAuditDto extends WriterAudit {
	/**
	 * 开始时间
	 */
	private String startDate;

	/**
	 * 结束时间
	 */
	private String endDate;

	/*
	 * 排序方式
	 */
	private String orderBy;

	/*
	 * 分页-当前第几页
	 */
	private Integer pageNo = 1;

	/*
	 * 分页-每页显示记录数量
	 */
	private Integer pageSize = 10;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}

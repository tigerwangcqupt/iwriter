package com.yryz.writer.common.redis;

public class Constant {
	
	public static final String SOURCE_USER = "USER";

	public static final String SOURCE_COUNTS = "COUNTS";
	

	/**
	 * 实时统计数据链表最大长度
	 */
	public static final int LIST_MAXSIZE = 10000;

	/**
	 * 排序方式
	 */
	public static enum Sort {
		DESC, ASC
	}
}

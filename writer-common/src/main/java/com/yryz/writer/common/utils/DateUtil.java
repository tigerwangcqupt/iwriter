package com.yryz.writer.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yryz.writer.common.exception.YyrzPcException;

public class DateUtil {

	public static String getNYRString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = simpleDateFormat.format(date);
		return s;
	}

	public static String getString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = simpleDateFormat.format(date);
		return s;
	}

	public static Date getDate(String date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw YyrzPcException.busiError( "日期转换异常！");
		}
		return parse;
	}

	/**
	 * 给时间加上几个小时
	 * 
	 * @param date
	 *            当前时间
	 * @param hour
	 *            需要加的时间
	 * @return Date
	 */
	public static Date addDateMinut(Date date, int hour) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		if (date == null)
			return null;
		// System.out.println("front:" + format.format(date)); //显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);// 24小时制
		date = cal.getTime();
		// System.out.println("after:" + format.format(date)); //显示更新后的日期
		cal = null;
		// return format.format(date);
		return date;
	}

	/*
	 * 获取当前时间之前或之后几分钟 minute
	 */
	public static String getTimeByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}


	/**
	 * date to timestamp
	 * @param s
	 * @return
	 */
	public static String dateToStamp(String s){
		String res="";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
		}catch(Exception e){
			throw YyrzPcException.busiError( "日期转换异常！");
		}
		return res;
	}

	/**
	 * timestamp to date
	 * @param s
	 * @return
	 */
	public static String stampToDate(String s){
		String res="";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 判断start和end相差多少分钟
	 * @param start
	 * @param end
	 * @return
	 */
	public static int  getDiffMinutes(String start,Date end){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			long from = simpleFormat.parse(start).getTime();
			long to = end.getTime();
			int minutes = (int) ((to - from)/(1000 * 60));
			return minutes;
		}catch (Exception e){
			throw YyrzPcException.busiError( "日期转换异常！");
		}
	}

	/**
	 * 判断start和end相差多少秒
	 * @param start
	 * @param end
	 * @return
	 */
	public static int  getDiffSeconds(String start,Date end){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			long from = simpleFormat.parse(start).getTime();
			long to = end.getTime();
			int minutes = (int) ((to - from)/(1000));
			return minutes;
		}catch (Exception e){
			throw YyrzPcException.busiError( "日期转换异常！");
		}
	}

	public static void main(String[] args) throws ParseException {
		String a = getTimeByMinute(-30);
		System.out.println(a);
		System.out.println(getDiffSeconds("2018-02-07 14:58:09",new Date()));

	}


}

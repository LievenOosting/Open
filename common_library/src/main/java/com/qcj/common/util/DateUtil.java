package com.qcj.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * author：qiuchunjia time：下午2:19:43 类描述：这个类是实现时间与时间戳的转换
 *
 */

public class DateUtil {
	/**
	 * 将年月日转换为 时间戳 例如 2015年7月12日
	 * 
	 * @param year_month_day
	 * @return
	 */
	public static String dateToStr(String year_month_day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d号");
			Date d = sdf.parse(year_month_day);
			long unixTimestamp = d.getTime() / 1000;
			Log.i("time", "dateToStr--------->" + unixTimestamp);
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String dateToStr2(String year_month_day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(year_month_day);
			long unixTimestamp = d.getTime() / 1000;
			Log.i("time", "dateToStr--------->" + unixTimestamp);
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressLint("SimpleDateFormat")
	public static String dateToStr3(String year_month_day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date d = sdf.parse(year_month_day);
			long unixTimestamp = d.getTime() / 1000;
			Log.i("time", "dateToStr--------->" + unixTimestamp);
			return String.valueOf(unixTimestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间戳转为年月日时间
	 * 
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String strTodate(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressLint("SimpleDateFormat")
	public static String strTodate2(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressLint("SimpleDateFormat")
	public static String strTodate3(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressLint("SimpleDateFormat")
	public static String strTodate4(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressLint("SimpleDateFormat")
	public static String strTodate5(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String stamp2humanDate(String time) {
		try {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d号");
			SimpleDateFormat simpformat = new SimpleDateFormat("HH:mm"); // 转换为小时和时间
			SimpleDateFormat complexFormat = new SimpleDateFormat("M月d号  HH:mm");
			/********* 当前时间与当前时间的参数获取 *****************/
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			String dbeforeStr = format.format(dBefore);
			/***********************************/
			String dCurrent = format.format(unixTime);
			if (dbeforeStr.compareTo(dCurrent) > 0) {
				return complexFormat.format(unixTime);
			} else if (dbeforeStr.compareTo(dCurrent) == 0) {
				return "昨天" + simpformat.format(unixTime);
			} else if (dbeforeStr.compareTo(dCurrent) < 0) {
				return simpformat.format(unixTime);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把时间戳转化为date
	 * 
	 * @param time
	 * @return
	 */
	public static Date stampToDate(String stamp) {
		if (stamp != null) {
			Long timestamp = Long.valueOf(stamp) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			return unixTime;
		}
		return null;
	}

	/**
	 * 把最原始的date转换为字符串时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToStamp(Date date) {
		if (date != null) {
			long unixTimestamp = date.getTime() / 1000;
			return String.valueOf(unixTimestamp);
		}
		return null;
	}

	/**
	 * 从时间戳当中获取当前年
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String StampToYear(String stamp) {
		Date date = stampToDate(stamp);
		if (date != null) {
			return String.valueOf(1900+date.getYear());
		}
		return null;
	}

	/**
	 * 从时间戳当中获取当前月
	 * 
	 * @return
	 */
	public static String StampToMonth(String stamp) {
		Date date = stampToDate(stamp);
		if (date != null) {
			return String.valueOf(date.getMonth());
		}
		return null;
	}

	/**
	 * 从时间戳当中获取当前日
	 * 
	 * @return
	 */
	public static String StampToDay(String stamp) {
		Date date = stampToDate(stamp);
		if (date != null) {
			return String.valueOf(date.getDay());
		}
		return null;
	}

	/**
	 * 通过时间戳获取当前是星期几
	 * 
	 * @param stamp
	 * @return
	 */
	public static String StampToWeek(String stamp) {
		String[] weekDays = { "SUN", "MON", "TUE", " WED", "THU", " FRI", "SAT" };
		Date date = stampToDate(stamp);
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		}
		return null;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean compareDate(Date first, Date second) {
		if (first != null && second != null) {
			if (first.getTime() > second.getTime()) {
				return true;
			}
			return false;
		}
		return false;

	}
	
	/**
	 * 将时间字符串改为long
	 * */
	public static long getYearMonDay(String time){
		SimpleDateFormat mFormat = new SimpleDateFormat(
				"yyyy-MM-dd", Locale.CHINA);
		Date mDate;
		try {
			mDate = mFormat.parse(time);
			return mDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			Log.d("Cathy", e.toString());
		}
		return 0;
	}
	
	public static String getYearMonDay(long time){
		SimpleDateFormat mFormat = new SimpleDateFormat(
				"yyyy-MM-dd", Locale.CHINA);
		Date date = new Date(time);
		return mFormat.format(date);
	}
	/**
	 * @param int
	 *            daily 间隔天数
	 * @return long 将天数转换为毫秒数
	 */
	public static long setIntervalMillis(int daily) {
		return daily * 24 * 60 * 60 * 1000;
	}
	
	/**
	 * 将时间字符串转换为long
	 */
	@SuppressLint("SimpleDateFormat")
	public static long changeStr2Long(String time) {
		SimpleDateFormat mFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		Date mDate;
		try {
			mDate = mFormat.parse(time);
			return mDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			Log.d("Cathy", e.toString());
		}
		return 0;
	}
	public static String changeLong2Str(long time) {
		SimpleDateFormat mFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date date = new Date(time);
		return mFormat.format(date);
	}
}

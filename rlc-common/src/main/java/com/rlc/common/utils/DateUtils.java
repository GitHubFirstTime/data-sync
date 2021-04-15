/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.rlc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author jeeplus
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 转换 x天x时x分
     *
     * @param minute 分钟
     * @return
     */
    public static String formatTime(long minute) {
        long day = minute / (24 * 60);
        long hour = (minute / (60) - day * 24);
        long min = (minute - day * 24 * 60 - hour * 60);
        return (day > 0 ? day + "天" : "") + (hour > 0 ? hour + "小时" : "") + min + "分钟";
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 倒计时
     *
     * @param startDate
     * @return[天，小时，分钟，秒]
     */
    public static String[] countDown(Date startDate) {
        Long currentTime = new Date().getTime();
        Long beforeTime = startDate.getTime();
        Long distTime = beforeTime - currentTime;
        Long day = 0l, hour = 0l, minutes = 0l, seconds = 0l;
        if (distTime > 0) {
            day = ((distTime / 1000) / (3600 * 24));
            hour = ((distTime / 1000) - day * 86400) / 3600;
            minutes = ((distTime / 1000) - day * 86400 - hour * 3600) / 60;
            seconds = (distTime / 1000) - day * 86400 - hour * 3600 - minutes * 60;
        }
        String[] countDownArr = {day.toString(), hour > 9 ? hour.toString() : "0" + hour, minutes > 9 ? minutes.toString() : "0" + minutes, seconds > 9 ? seconds.toString() : "0" + seconds,};
        return countDownArr;
    }


    /**
     * 分钟差
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static long diffMinutes(Date endDate, Date startDate) {
        long diff = (endDate.getTime() - startDate.getTime()) / (1000 * 60);
        return diff;
    }

    /**
     * 秒差
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static long diffSecond(Date endDate, Date startDate) {
        long diff = (endDate.getTime() - startDate.getTime()) / (1000);
        return diff;
    }

    public static String getDiffTimeStr(Date endDate, Date startDate) {
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;
        //获得两个时间的毫秒时间差异
        long diff;
        diff = endDate.getTime() - startDate.getTime();
        String day = diff / nd + "";//计算差多少天
        String hour = diff % nd / nh + "";//计算差多少小时
        String min = diff % nd % nh / nm + "";//计算差多少分钟
        String sec = diff % nd % nh % nm / ns + "";//计算差多少秒//输出结果
        if (day.equals("0")) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        } else if (day.equals("0") && hour.equals("0")) {
            return min + "分钟" + sec + "秒";
        } else if (day.equals("0") && hour.equals("0") && min.equals("0")) {
            return sec + "秒";
        } else {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
    }

    public static String getPreMonthDateStr() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        Date day = c.getTime();
        String str = new SimpleDateFormat("yyyy-MM-dd").format(day);
        return str;
    }

    public static String getNextDayStr(Calendar c) {
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String str = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return str;
    }

    public static String getSomeBeforeDayStr(int beforeDay) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) - beforeDay);
        Date day = c.getTime();
        String str = new SimpleDateFormat("yyyy-MM-dd").format(day);
        return str;
    }

    public static Set<String> getDateStrBetween(Date startDate, Date endDate) {
        TreeSet<String> set = new TreeSet<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            String format = sdf.format(result);
            set.add(format.substring(0, 4) + format.substring(5, 7));
        }
        return set;
    }

    public static String getDiffTimeStrWithState(Date endDate, Date startDate) {
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;
        //获得两个时间的毫秒时间差异
        long diff;
        diff = endDate.getTime() - startDate.getTime();
        if (diff <= 0) {
            return "门禁权限关闭或已经到期";
        }
        String day = diff / nd + "";//计算差多少天
        String hour = diff % nd / nh + "";//计算差多少小时
        String min = diff % nd % nh / nm + "";//计算差多少分钟
        String sec = diff % nd % nh % nm / ns + "";//计算差多少秒//输出结果
        if (day.equals("0")) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        } else if (day.equals("0") && hour.equals("0")) {
            return min + "分钟" + sec + "秒";
        } else if (day.equals("0") && hour.equals("0") && min.equals("0")) {
            return sec + "秒";
        } else {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
    }

    public static String getFirstAndLastOfMonth(String dataStr, String dateFormat, String resultDateFormat) throws Exception {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
//        c.setTime(new SimpleDateFormat(dateFormat).parse(dataStr));
//        c.setTime(new Date());
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = new SimpleDateFormat(resultDateFormat).format(c.getTime());
        System.out.println("===============first:" + first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = new SimpleDateFormat(resultDateFormat).format(ca.getTime());
        System.out.println("===============last:" + last);
        return first + "_" + last;
    }


    /**
     * compareDate:
     * 比较两个时间大小
     *
     * @param dateA
     * @param dateB
     * @return boolean dateA >= dateB 返回true 否则返回 false
     * @author RLC_ZYC
     * @since JDK 1.8
     */
    public static boolean compareDate(Date dateA, Date dateB) {
        boolean flag = false;
        flag = dateA.getTime() >= dateB.getTime();
        return flag;
    }
    /**
     * compareDate:
     * 比较两个时间大小
     *
     * @param dateA
     * @param dateB
     * @return boolean dateA <= dateB 返回true 否则返回 false
     * @author RLC_ZYC
     * @since JDK 1.8
     */
    public static boolean compareDatesm(Date dateA, Date dateB) {
        boolean flag = false;
        flag = dateA.getTime() <= dateB.getTime();
        return flag;
    }
    public static boolean recordIsYesterDay(Long time) {
        String format = sdf.format(time);
        Calendar calendar = Calendar.getInstance();
        String format1 = sdf.format(calendar.getTime());
        return format.equals(format1);
    }

    public static boolean isDiffDay(Long time, Long time2) {
        String format = sdf.format(time);
        String format1 = sdf.format(time2);
//        System.out.println("time:"+time+" time2:"+time2);
        boolean b = !format.equals(format1);
        if (b) {
            System.out.println("format:" + format + " format1:" + format1);
        }
        return !format.equals(format1);
    }

    /**
     * 是否在昼伏夜出时间段
     *
     * @param startTime
     * @param endTime
     * @param recordTime
     * @return
     */
    public static boolean isInCheckTime(String startTime, String endTime, Long recordTime) throws ParseException {
        SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdfLong.format(recordTime).substring(0, 11);
        String s1 = format + " " + startTime;
        String s2 = format + " " + endTime;
        System.out.println("s1:" + s1);
        System.out.println("s2:" + s2);
        long timeStart = sdfLong.parse(s1).getTime();
        long timeEnd = sdfLong.parse(s2).getTime();
        System.out.println("---");
        System.out.println(timeStart);
        System.out.println(recordTime);
        System.out.println(timeEnd);
        System.out.println("---");
        boolean b;
        if (timeStart > timeEnd) {//昼伏夜出时间段不在一天
            b = ((recordTime >= timeStart) || (recordTime <= timeEnd));
        } else {//昼伏夜出时间段在一天
            b = ((recordTime >= timeStart) && (recordTime <= timeEnd));
        }
        System.out.println("isInCheckTime:" + b);
        return b;
    }

    /**
     * 是否在一个昼伏夜出时间段
     *
     * @param time1
     * @param time2
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static boolean isInSameCheckTime(Long time1, Long time2, String startTime, String endTime) throws ParseException {
        long start = System.currentTimeMillis();
        SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = sdfLong.format(time1).substring(0, 11);
        String format2 = sdfLong.format(time2).substring(0, 11);
        boolean b = false;
        if (format1.equals(format2)) {//日期相等
            long timeStart = sdfLong.parse(format1 + " " + startTime).getTime();
            long timeEnd = sdfLong.parse(format1 + " " + endTime).getTime();
            if (timeStart > timeEnd) {//昼伏夜出时间段不在一天
                if ((time1 >= timeStart && time2 >= timeStart) || (time1 <= timeEnd && time2 <= timeEnd)) {//且在一个段
                    b = true;
                }
            } else {
                if (time1 >= timeStart && time2 >= timeStart && time1 <= timeEnd && time2 <= timeEnd) {
                    b = true;
                }
            }
        } else {//日期不等,要满足的话，昼伏夜出时间段必然不在一天
            if (time1 > time2) {//format1>format2
                boolean b1 = (time1 <= (sdfLong.parse(format1 + " " + endTime).getTime())
                        && (time1 >= (sdf.parse(format1).getTime())));
                boolean b2 = (time2 >= (sdfLong.parse(format2 + " " + startTime).getTime())
                        && (time2 <= (sdf.parse(format1).getTime())));
                if (b1 && b2) {
                    b = true;
                }
            } else {//time2>time1,format2>format1
                boolean b2 = (time2 <= (sdfLong.parse(format2 + " " + endTime).getTime())
                        && (time2 >= (sdf.parse(format2).getTime())));
                boolean b1 = (time1 >= (sdfLong.parse(format1 + " " + startTime).getTime())
                        && (time1 <= (sdf.parse(format2).getTime())));
                if (b1 && b2) {
                    b = true;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "毫秒");
        return b;
    }

    /**
     * 得到属于昼伏夜出时间段的一个时间点的昼伏夜出时间结束点
     * @param time
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static String getCheckTimeEnd(Long time, String startTime, String endTime) throws ParseException {
        String returnStr;
        SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(time);
//        String format1 = format1a.substring(0, 11);
        long timeStart = sdfLong.parse(format + " " + startTime).getTime();
        long timeEnd = sdfLong.parse(format + " " + endTime).getTime();
        if(timeEnd>timeStart){//昼伏夜出时间段在一天
            returnStr= sdfLong.format(timeEnd);
        }else {
            if(timeEnd>time){//在昼伏夜出时间段的后半段
                returnStr=sdfLong.format(timeEnd);
            }else {
                returnStr=sdfLong.format(timeEnd+24*60*60*1000);
            }
        }
        System.out.println("昼伏夜出时间段结束点："+returnStr);
        return returnStr;
    }

    /**
     * 获得入参日期下个月的第一天
     *
     * @param date 当前时间
     * @return
     */
    public static Date getNextMonthStart(Date date) {
        //获得入参的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获取下个月第一天：
        calendar.add(Calendar.MONTH, 1);
        //设置为1号,当前日期既为次月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 明天的起始时刻
     *
     * @param date
     * @return
     */
    public static Date getNextDayStart(Date date) {
        //获得入参的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获取下个月第一天：
        calendar.add(Calendar.DATE, 1);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 这个月的起始时刻
     *
     * @param date
     * @return
     */
    public static Date getThisMonthStart(Date date) {
        //获得入参的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //设置为1号,当前日期既为次月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 这一天的起始时刻
     *
     * @param date
     * @return
     */
    public static Date getThisDayStart(Date date) {
        //获得入参的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Map<String, Long> getMonthlyStartDateMapThisYear(Date date) {
        //一月 Jan. January
        //二月 Feb. February
        //三月 Mar. March
        //四月 Apr. April
        //五月 May. May
        //六月 Jun. June
        //七月 Jul. July
        //八月 Aug. August
        //九月 Sept. September
        //十月 Oct. October
        //十一月 Nov. November
        //十二月 Dec. December
        HashMap<String, Long> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        for (int i = 0; i <= 11; i++) {
            calendar.set(Calendar.MONTH, i);
            map.put("month" + (i+1), calendar.getTime().getTime());
        }
        //明年的开始
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, 0);
        Date month13 = calendar.getTime();
        map.put("month13", month13.getTime());
        return map;
    }

    /**
     * 首页开门实时数据要拿到节点时间
     * 总共14个点 time0-time1属于0点的，time1-time2属于1点的。。。time24-time25属于24点的
     * @param date
     * @return
     */
    public static Map<String, Long> get24HoursPoint(Date date) {
        HashMap<String, Long> map = new HashMap<>();
        long time0 = getThisDayStart(date).getTime();
        map.put("time0", time0);
        for (int i = 1; i <= 25; i++) {
            if (i == 1 || i == 25) {
                time0 = time0 + 1000 * 60 * 30;//加半小时
            } else {
                time0 = time0 + 1000 * 60 * 60;//加一个小时
            }
            map.put("time" + i, time0);
        }
        return map;
    }
    public static void main(String[] args) throws Exception {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//        System.out.println(getDate());
//        System.out.println(getPreMonthDateStr());
        /*Set<String> datesBetween = getDateStrBetween(sdf.parse("2019-01-01"), sdf.parse("2020-01-02"));
        System.out.println(datesBetween);*/
        System.out.println(getFirstAndLastOfMonth(null,null,"yyyy-MM-dd"));
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        String last = sdfShort.format(ca.getTime());

//        openingRecord.setSearchDateEnd(DateUtils.getDate());
        //截止日期处理
//        Date dateEnd = sdfShort.parse(openingRecord.getSearchDateEnd());
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dateEnd);
        String nextDayStr = DateUtils.getNextDayStr(ca);
        System.out.println(nextDayStr);
    }
}

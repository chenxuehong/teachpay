package com.huihe.base_lib.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.huihe.base_lib.R;
import com.huihe.base_lib.ui.activity.BaseApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 * Created by bob on 2015/2/28.
 */
public class DateUtils {
    public final static String TAG = DateUtils.class.getSimpleName();
    public final static long min_10 = 1000 * 60 * 10;
    private static final String FIRST_HMS = "00:00:00";
    private static final String END_HMS = "23:59:59";

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final String YMFormatStr = "yyyy-MM";
    public static final String YMFormatStr3 = "yyyy.MM";
    public static final String YMDFormatStr = "yyyy-MM-dd";
    public static final String YMDFormatStr4 = "yyyy.MM.dd";
    public static final String YFormatStr = "yyyy";
    public static final String YMDFormatStr2 = "yyyy年MM月dd日";
    public static final String YMDFormatStr3 = "yy/MM/dd";
    public static final String YMFormatStr2 = "yyyy-MM";
    public static final String YFormatStr2 = "yyyy";
    public static final String MFormatStr2 = "MM";
    public static final String DFormatStr2 = "dd";
    public static final String YMDHMSFormatStr = "yyyy-MM-dd HH:mm:ss";
    public static final String HMFormatStr = "HH:mm";
    public static final String HFormatStr = "HH";
    public static final String MFormatStr = "mm";
    public static final String HMSFormatStr = "HH:mm:ss";
    public static final String YMDHMFormatStr = "yyyy-MM-dd HH:mm";
    public static final String MDHMFormatStr = "MM-dd HH:mm";
    public static String MDFormatStr = "MM-dd";

    public static String dayOfString(long millisecond, String format) {
        Date date = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static int getMonthOfDay(int year, int month) {
        int day = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            day = 29;
        } else {
            day = 28;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return day;

        }

        return 0;
    }

    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     */
    public static String convertTimeToFormat(long timeStamp) {
        if (timeStamp > 852048000000L) {//如果是秒的话 28970/4/30 0:0:0
            timeStamp = timeStamp / 1000;
        }
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return BaseApplication.getInstance().getResources().getString(R.string.just_now);

        } else if (time >= 60 && time < 3600) {
            String trs = BaseApplication.getInstance().getResources().getString(R.string.Minutes_ago);
            return time / 60 + trs;
        } else if (time >= 3600 && time < 3600 * 24) {
            String trs = BaseApplication.getInstance().getResources().getString(R.string.Hours_before);
            return time / 3600 + trs;
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            String trs = BaseApplication.getInstance().getResources().getString(R.string.Days_ago);
            return time / 3600 / 24 + trs;
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            String trs = BaseApplication.getInstance().getResources().getString(R.string.Months_ago);
            return time / 3600 / 24 / 30 + trs;
        } else if (time >= 3600 * 24 * 30 * 12) {
            String trs = BaseApplication.getInstance().getResources().getString(R.string.years_ago);
            return time / 3600 / 24 / 30 / 12 + trs;
        } else {
            return BaseApplication.getInstance().getResources().getString(R.string.just_now);
        }
    }

    /**
     * 日期格式化
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        return dateFm.format(Long.valueOf((new Date()).getTime()));
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurDateStr(String formatTimeStr) {
        if (TextUtils.isEmpty(formatTimeStr)) {
            return "";
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(formatTimeStr, Locale.SIMPLIFIED_CHINESE);
        return dateFm.format(Long.valueOf((new Date()).getTime()));
    }

    /**
     * 指定时间格式化字符串
     *
     * @return
     */
    public static String getDateStr(String time, String formatTimeStr) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(formatTimeStr, Locale.SIMPLIFIED_CHINESE);
        return dateFm.format(Long.valueOf(time));
    }

    /**
     * 指定时间格式化字符串
     *
     * @return
     */
    public static String getDateStr(long time, String formatTimeStr) {
        if (TextUtils.isEmpty(formatTimeStr)) {
            return "";
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(formatTimeStr, Locale.SIMPLIFIED_CHINESE);
        return dateFm.format(time);
    }

    /**
     * 将一个时间转换成提示性时间字符串
     */
    public static String convertTimeToString(long time) {
        if (time < 60 && time >= 0) {
            return time + "秒";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分" + time % 60 + "秒";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时" + (time % 3600) / 60 + "分" + (time % 3600) % 60 + "秒";
        } else {
            return "0秒";
        }
    }

    /**
     * 将一个时间转换成提示性时间字符串
     */
    public static String convertTimeToString2(long time) {
        if (time >= 0 && time < 1000 * 60) {
            long ss = time / 1000;
            if (time < 10) {

                return "00:" + "0" + ss;
            } else {
                return "00:" + ss;
            }
        } else if (time >= 60 * 1000 && time < 3600 * 1000) {

            long f = time / (60 * 1000);
            String fStr = "00:";
            if (f < 10) {
                fStr = "0" + f;
            } else {
                fStr = "" + f;
            }

            long ss = time % (60 * 1000);
            String sStr = "";
            if (time < 10) {

                sStr = "0" + ss;
            } else {
                sStr = "" + ss;
            }
            return fStr + ":" + sStr;
        } else if (time >= 3600 * 1000 && time < 3600 * 24 * 1000) {

            long h = time / (3600 * 1000);
            String HStr = "";
            if (h < 10) {
                HStr = "0" + h;
            } else {
                HStr = "" + h;
            }
            long f = (time % (3600 * 1000)) / 60;
            String fStr = "";
            if (f < 10) {
                fStr = "0" + f;
            } else {
                fStr = "" + f;
            }

            long ss = (time % (3600 * 1000)) % 60;
            String sStr = "";
            if (time < 10) {

                sStr = "0" + ss;
            } else {
                sStr = "" + ss;
            }
            return HStr + ":" + fStr + ":" + ss;
        } else {
            return "00:00";
        }
    }

    public static String getStringDate(long ldate, String formats) {
        if (ldate == 0) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(formats);
        String date = sdf.format(new Date(ldate));
        return date;
    }


    public static String getStringHHMMDate(long ldate) {
        if (ldate == 0) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(new Date(ldate));
        return date;
    }

    public static String dayOfYMD(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String dayOfMD(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(date);
    }

    public static String dayOfMDHHMMSS(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String dayOfHH(long millisecond) {
        Date date = new Date(millisecond);
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(date);
    }

    public static String dayOfStr(long millisecond) {
        Date date = new Date(millisecond);
        return simpleDateFormat.format(date);
    }

    /**
     * 时间戳转换成按间隔区分的初始小时
     *
     * @param ldate
     * @return
     */
    public static long getStandardTime(long ldate, int hourInterval) {
        Date startDate = new Date(ldate);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            String date = sdf.format(new Date(ldate));
            Log.i(TAG, "getIntHour time:" + date);
            int hour = Integer.parseInt(date);
            hour = (hour / hourInterval) * hourInterval;
            startDate.setHours(hour);
            startDate.setMinutes(0);
            startDate.setSeconds(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startDate.getTime();
    }


    public static String longFormatToHHmm(long duration) {
        StringBuffer result = new StringBuffer();
        int hour = (int) (duration / (60 * 60 * 1000));
        int min = (int) ((duration % (60 * 60 * 1000)) / (60 * 1000));
        if (hour < 10) {
            result.append("0");
        }
        result.append(hour);
        result.append(":");
        if (min < 10) {
            result.append("0");
        }
        result.append(min);
        return result.toString();
    }

    /**
     * 秒转成时分秒
     *
     * @param duration
     * @return
     */
    public static String longFormatToHHmmss(long duration) {
        StringBuffer result = new StringBuffer();
        int hour = (int) (duration / (60 * 60));
        int min = (int) ((duration - hour * 60 * 60) / 60);
        int ss = (int) (duration - hour * 60 * 60 - min * 60);

        if (hour < 10) {
            result.append("0");
        }

        result.append(hour);
        result.append(":");
        if (min < 10) {
            result.append("0");
        }
        result.append(min);
        result.append(":");
        if (ss < 10) {
            result.append("0");
        }
        result.append(ss);
        return result.toString();
    }

    /**
     * 将毫秒转化成固定格式的时间
     * 时间格式: yyyy-MM-dd
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(Long millisecond, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(millisecond);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取时间间隔
     * "yyyy-MM-dd"
     *
     * @param millisecond        之前的时间（毫秒）
     * @param currentMillisecond 当前时间（毫秒）
     * @return
     */
    public static String getSpaceTime(Long millisecond, Long currentMillisecond, String pattern) {

        //间隔秒
        Long spaceSecond = (currentMillisecond - millisecond) / 1000;

        //间隔分
        Long spaceMinute = spaceSecond / 60;

        //间隔时
        Long spaceHour = spaceMinute / 60;

        //间隔天
        Long spaceDay = spaceHour / 24;


        //region 一分钟之内
        if (spaceSecond >= 0 && spaceSecond < 60) {
            return "刚刚";
        }
        //endregion

        //region 一小时之内
        if (spaceMinute >= 1 && spaceMinute < 59) {
            return (spaceMinute + 1) + "分钟前";
        }
        //endregion

        if (spaceMinute >= 59 && spaceMinute < 60) {
            return "1小时前";
        }

        //region 一天之内
        if (spaceHour >= 1 && spaceHour < 23) {
            return (spaceHour + 1) + "小时前";
        }
        //endregion

        if (spaceHour >= 23 && spaceHour < 24) {
            return "1天前";
        }

        //7天之内
        if (spaceDay >= 1 && spaceDay < 7) {
            return (spaceDay + 1) + "天前";
        }
        //endregion

        return getDateTimeFromMillisecond(millisecond, pattern);
    }


    public static String getFormatItemShowTimeByMis(SimpleDateFormat simpleDateFormat, long mis) {
        return getSpaceTime(mis, System.currentTimeMillis(), "yyyy-MM-dd");
    }


    public static String getFormatItemShowTimeByMis(long mis) {
        return getFormatItemShowTimeByMis(simpleDateFormat, mis);
    }

    public static Date stringToDate(String strTime, String formatType) {
        if (TextUtils.isEmpty(strTime)) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatType);
            date = formatter.parse(strTime);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static long stringToLong(String strTime, String formatStr) {
        Date date = stringToDate(strTime, formatStr); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间的星期
     *
     * @return
     */
    public static int getCurDayofWeek() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return i - 1;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay, String formatTimeStr, int days) {
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(formatTimeStr).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - days);

        String dayBefore = new SimpleDateFormat(formatTimeStr).format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, String formatTimeStr, int days) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(formatTimeStr).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);

        String dayAfter = new SimpleDateFormat(formatTimeStr).format(c.getTime());
        return dayAfter;
    }

    /**
     * 获得格式转化
     *
     * @param dateStr
     * @return
     */
    public static String getOtherDateStr(String dateStr, String formatTimeStr, String otherFormatTimeStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(formatTimeStr).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        String dayAfter = new SimpleDateFormat(otherFormatTimeStr).format(c.getTime());
        return dayAfter;
    }


    /**
     * 获取指定日期是周几
     *
     * @param specifiedDay
     * @param formatTimeStr
     * @return
     */
    public static int getDayofWeek(String specifiedDay, String formatTimeStr) {
        Calendar cal = Calendar.getInstance();
        if (specifiedDay.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(formatTimeStr, Locale.getDefault());
            Date date;
            try {
                date = sdf.parse(specifiedDay);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
            if (date != null) {
                cal.setTime(new Date(date.getTime()));
            }
        }
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    private static String[] weeks = {
            "周一",
            "周二",
            "周三",
            "周四",
            "周五",
            "周六",
            "周日"
    };

    private static String[] englishWeeks = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Weekday"
    };

    public static String getChineseWeek(String specifiedDay, String formatTimeStr) {
        int dayofWeek = getDayofWeek(specifiedDay, formatTimeStr);
        if (dayofWeek == 0) {
            dayofWeek = 6;
        }
        if (dayofWeek > 0) {
            dayofWeek--;
        }
        return weeks[dayofWeek];
    }

    public static String getEngLishWeek(String specifiedDay, String formatTimeStr) {
        int dayofWeek = getDayofWeek(specifiedDay, formatTimeStr);
        if (dayofWeek == 0) {
            dayofWeek = 6;
        }
        if (dayofWeek > 0) {
            dayofWeek--;
        }
        return englishWeeks[dayofWeek];
    }

    public static float compareDateStr(String dateStr, String curDateStr, String formatTimeStr) {

        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat(formatTimeStr);
        try {
            Date beginTime = CurrentTime.parse(curDateStr);
            Date endTime = CurrentTime.parse(dateStr);
            return ((endTime.getTime() - beginTime.getTime()) * 1f / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1f;
    }

    public static float getCurTimeZoneOffset() {
        Calendar mDummyDate;
        mDummyDate = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        mDummyDate.setTimeZone(now.getTimeZone());
        mDummyDate.set(now.get(Calendar.YEAR), 11, 31, 13, 0, 0);
        String gmt = getTimeZoneText(now.getTimeZone(), false).replaceAll("GMT", "");
        float timeZoneOffset = 0.0f;
        if (gmt.contains("+")) {
            gmt = gmt.replaceAll("\\+", "");
            String[] split = gmt.split(":");
            float point = NumberUtils.tranToADecimal(Math.abs(Integer.valueOf(split[1])) * 1f / 60);
            int preNum = Math.abs(Integer.valueOf(split[0]));
            timeZoneOffset = preNum + point;
        } else if (gmt.contains("-")) {
            gmt = gmt.replaceAll("\\+", "");
            String[] split = gmt.split(":");
            float point = NumberUtils.tranToADecimal(Math.abs(Integer.valueOf(split[1])) * 1f / 60);
            int preNum = Math.abs(Integer.valueOf(split[0]));
            timeZoneOffset = -(preNum + point);
        }
        return timeZoneOffset;
    }

    public static String getTimeZoneText(TimeZone tz, boolean includeName) {
        Date now = new Date();
        SimpleDateFormat gmtFormatter = new SimpleDateFormat("ZZZZ");
        gmtFormatter.setTimeZone(tz);
        String gmtString = gmtFormatter.format(now);
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        Locale l = Locale.getDefault();
        boolean isRtl = TextUtils.getLayoutDirectionFromLocale(l) == View.LAYOUT_DIRECTION_RTL;
        gmtString = bidiFormatter.unicodeWrap(gmtString,
                isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);

        if (!includeName) {
            return gmtString;
        }

        return gmtString;
    }

    public static String getFirstHMS() {
        return FIRST_HMS;
    }

    public static String getEndHMS() {
        return END_HMS;
    }

    /**
     * 根据年份及月份计算每月的天数
     */
    public static int calculateDaysInMonth(int year, int month) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] bigMonths = {"1", "3", "5", "7", "8", "10", "12"};
        String[] littleMonths = {"4", "6", "9", "11"};
        List<String> bigList = Arrays.asList(bigMonths);
        List<String> littleList = Arrays.asList(littleMonths);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (bigList.contains(String.valueOf(month))) {
            return 31;
        } else if (littleList.contains(String.valueOf(month))) {
            return 30;
        } else {
            if (year <= 0) {
                return 29;
            }
            // 是否闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    /**
     * 月日时分秒，0-9前补0
     */
    @NonNull
    public static String fillZero(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

    /**
     * 截取掉前缀0以便转换为整数
     *
     * @see #fillZero(int)
     */
    public static int trimZero(@NonNull String text) {
        try {
            if (text.startsWith("0")) {
                text = text.substring(1);
            }
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            LogUtils.warn(e);
            return 0;
        }
    }

    /**
     * 功能：判断日期是否和当前date对象在同一天。
     * 参见：http://www.cnblogs.com/myzhijie/p/3330970.html
     *
     * @param date 比较的日期
     * @return boolean 如果在返回true，否则返回false。
     */
    public static boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        Calendar nowCalendar = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        return (nowCalendar.get(Calendar.ERA) == newCalendar.get(Calendar.ERA) &&
                nowCalendar.get(Calendar.YEAR) == newCalendar.get(Calendar.YEAR) &&
                nowCalendar.get(Calendar.DAY_OF_YEAR) == newCalendar.get(Calendar.DAY_OF_YEAR));
    }

    public static String getApm(long time) {
        String date = "";
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);

        int hour = mCalendar.get(Calendar.HOUR);
        int apm = mCalendar.get(Calendar.AM_PM);
        if (apm == 0) {
            date = "上午";
        } else {
            date = "下午";
        }
        return date;
    }

    public static String getApm(String time, String formatStr) {
        Date date = stringToDate(time, formatStr);
        GregorianCalendar ca = new GregorianCalendar();
        ca.setTime(date);
        return ca.get(GregorianCalendar.AM_PM) == Calendar.PM ? "下午" : "上午";
    }

    public static int getCurWeekIndex() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setTime(new Date());
        //获取当前时间为本月的第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week;
    }

    public static int getWeekIndex(String dateStr, String formatType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        calendar.setMinimalDaysInFirstWeek(7);
        Date date = stringToDate(dateStr, formatType);
        calendar.setTime(date);
        //获取当前时间为本月的第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static long getDistanceMillis(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(YMDHMSFormatStr);
        Date one;
        Date two;
        long diff = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();

            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getTwoDateDifferenceDates(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)){
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String timeStr = "";
        if (day > 0) {
            if (MultiLanguageUtil.getInstance().isZh()) {
                timeStr = day + " 天";
            } else {
                timeStr = day + " Day";
            }

        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + hour + " 时";
        } else {
            timeStr = timeStr + " " + hour + " Hours";
        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + min + " 分";
        } else {
            timeStr = timeStr + " " + min + " Minutes";
        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + sec + " 秒";
        } else {
            timeStr = timeStr + " " + sec + " Seconds";
        }
        return timeStr;
    }

    public static String getDHMMSSistance(long diff) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String timeStr = "";
        if (day > 0) {
            if (MultiLanguageUtil.getInstance().isZh()) {
                timeStr = day + " 天";
            } else {
                timeStr = day + " Day";
            }

        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + hour + " 时";
        } else {
            timeStr = timeStr + " " + hour + " Hours";
        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + min + " 分";
        } else {
            timeStr = timeStr + " " + min + " Minutes";
        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            timeStr = timeStr + " " + sec + " 秒";
        } else {
            timeStr = timeStr + " " + sec + " Seconds";
        }
        return timeStr;
    }

    public static String getDistanceHMSTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String hourStr = String.valueOf(hour);
        if (hourStr.length() <= 1) {
            hourStr = "0" + hourStr;
        }
        String minStr = String.valueOf(min);
        if (minStr.length() <= 1) {
            minStr = "0" + minStr;
        }
        String secStr = String.valueOf(sec);
        if (secStr.length() <= 1) {
            secStr = "0" + secStr;
        }
        if (hour > 0) {
            return hourStr + ":" + minStr + ":" + secStr;
        } else {
            return minStr + ":" + secStr;
        }
    }

    public static String getDistanceHMSTime2(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String hourStr = String.valueOf(hour);
        if (hourStr.length() <= 1) {
            hourStr = "0" + hourStr;
        }
        String minStr = String.valueOf(min);
        if (minStr.length() <= 1) {
            minStr = "0" + minStr;
        }
        String secStr = String.valueOf(sec);
        if (secStr.length() <= 1) {
            secStr = "0" + secStr;
        }
        if (hour > 0) {
            return hourStr.concat("时").concat(minStr).concat("分").concat(secStr).concat("秒");
        } else if (min>0){
            return minStr.concat("分").concat(secStr).concat("秒");
        }else {
            return secStr.concat("秒");
        }
    }

    public static String getDistanceHMSTime(long diff) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        String hourStr = String.valueOf(hour);
        if (hourStr.length() <= 1) {
            hourStr = "0" + hourStr;
        }
        String minStr = String.valueOf(min);
        if (minStr.length() <= 1) {
            minStr = "0" + minStr;
        }
        String secStr = String.valueOf(sec);
        if (secStr.length() <= 1) {
            secStr = "0" + secStr;
        }
        if (hour > 0) {
            return hourStr + ":" + minStr + ":" + secStr;
        } else {
            return minStr + ":" + secStr;
        }

    }

    /**
     * 两个时间相差距离多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static long getDistanceTimeSS(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;

        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            sec = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sec;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime2(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)){
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (MultiLanguageUtil.getInstance().isZh()) {
            if (day > 0) {
                return day + "天"
                        .concat(String.valueOf(hour))
                        .concat("时")
                        .concat(String.valueOf(min))
                        .concat("分")
                        .concat(String.valueOf(sec))
                        .concat("秒");
            } else if (hour > 0) {
                return hour + "时"
                        .concat(String.valueOf(min))
                        .concat("分")
                        .concat(String.valueOf(sec))
                        .concat("秒");
            } else if (min > 0) {
                return min + "分钟"
                        .concat(String.valueOf(sec))
                        .concat("秒");
            } else if (sec > 0) {
                return sec + "秒";
            }
        } else {
            if (day > 0) {
                return day + " Day"
                        .concat(String.valueOf(hour))
                        .concat(" Hours")
                        .concat(String.valueOf(min))
                        .concat(" Minutes")
                        .concat(String.valueOf(sec))
                        .concat(" Seconds");
            } else if (hour > 0) {
                return hour + " Hours"
                        .concat(String.valueOf(min))
                        .concat(" Minutes")
                        .concat(String.valueOf(sec))
                        .concat(" Seconds");
            } else if (min > 0) {
                return min + " Minutes"
                        .concat(String.valueOf(sec))
                        .concat(" Seconds");
            } else if (sec > 0) {
                return sec + " Seconds";
            }
        }

        return "";
    }

    public static int birthdayToAge(String birthday) {
        if (TextUtils.isEmpty(birthday)) {
            return 0;
        }
        int age = 0;
        if (FormatUtil.isNumeric(birthday)) {
            age = birthdayToAge(Long.valueOf(birthday));

        } else {
            age = birthdayToAge(birthday, YMDFormatStr);
        }
        return age;
    }

    private static int birthdayToAge(String birthday, String formatStr) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        String year = DateUtils.getOtherDateStr(birthday, formatStr, YFormatStr);
        String month = DateUtils.getOtherDateStr(birthday, formatStr, MFormatStr2);
        String day = DateUtils.getOtherDateStr(birthday, formatStr, DFormatStr2);
        // 用当前年月日减去出生年月日
        int yearMinus = yearNow - Integer.valueOf(trimZero(year));
        int monthMinus = monthNow - Integer.valueOf(trimZero(month));
        int dayMinus = dayNow - Integer.valueOf(trimZero(day));
        int age = yearMinus;// 先大致赋值
        if (yearMinus <= 0) {
            age = 0;
        } else if (monthMinus < 0) {
            age = age - 1;
        } else if (monthMinus == 0) {
            if (dayMinus < 0) {
                age = age - 1;
            }
        }
        return age;
    }

    public static int birthdayToAge(long birthday) {

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);


        String year = DateUtils.getDateStr(birthday, YFormatStr);
        String month = DateUtils.getDateStr(birthday, MFormatStr2);
        String day = DateUtils.getDateStr(birthday, DFormatStr2);
        // 用当前年月日减去出生年月日
        int yearMinus = yearNow - Integer.valueOf(trimZero(year));
        int monthMinus = monthNow - Integer.valueOf(trimZero(month));
        int dayMinus = dayNow - Integer.valueOf(trimZero(day));
        int age = yearMinus;// 先大致赋值
        if (yearMinus <= 0) {
            age = 0;
        } else if (monthMinus < 0) {
            age = age - 1;
        } else if (monthMinus == 0) {
            if (dayMinus < 0) {
                age = age - 1;
            }
        }
        return age;
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }


    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }


    /**
     * 返回指定日期的月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    public static int getYear(String dateStr, String formatStr) {
        Date date = null;
        try {
            date = new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(String dateStr, String formatStr) {
        Date date = null;
        try {
            date = new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(String dateStr, String formatStr) {
        Date date = null;
        try {
            date = new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurHour() {

        String curDateStr = getCurDateStr(HFormatStr);
        return Integer.valueOf(curDateStr);
    }

    public static int getCurMinute() {

        String curDateStr = getCurDateStr(MFormatStr);
        return Integer.valueOf(curDateStr);
    }

    public static String translateZoneTimeStr(String start_time, Float otherOffset, Float curTimeZoneOffset, String formatStr) {

        Float hoursDistance = curTimeZoneOffset - otherOffset;
        float v = hoursDistance * 60 * 60 * 1000;
        long disTimeLong = (long) (v);
        long time1 = stringToLong(start_time, formatStr) + disTimeLong;
        try {
            String dateStr = longToString(time1, formatStr);
            return dateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String addMinuteTime(String dateStr, String formatStr, int minutes) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return getDateStr(calendar.getTimeInMillis(), YMDHMSFormatStr);
    }

    /**
     * 获取指定日期所在月份开始的时间
     * lkeji
     *
     * @return
     */
    public static String getMonthBegin(String specifiedDay, String formatStr) {
        Date data = null;
        try {
            data = new SimpleDateFormat(formatStr).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return sdf.format(date);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定日期所在月份结束的时间
     *
     * @return
     */
    public static String getMonthEnd(String specifiedDay, String formatStr) {
        Date data = null;
        try {
            data = new SimpleDateFormat(formatStr).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(data);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return sdf.format(date);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否为今天
     */
    public static boolean isToday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 0;
        }
        return false;
    }

    /**
     * 是否为昨天
     */
    public static boolean isYesterday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 1;
        }
        return false;
    }

    /**
     * 是否为前天
     */
    public static boolean isBeforeday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 2;
        }
        return false;
    }

    /**
     * @param timeStamp
     * @return
     * @desc 显示某个时间戳是今天、昨天、前天或星期几
     */
    public static String getWeekOrTodayORYesterdayOrBeforeday(Context context, long timeStamp, String formatTimeStr) {

        try {
            if (isToday(timeStamp)) {

                return context.getResources().getString(R.string.Today);
            } else if (isYesterday(timeStamp)) {
                return context.getResources().getString(R.string.yesterday);
            } else if (isBeforeday(timeStamp)) {
                return context.getResources().getString(R.string.beforeyesterday);
            } else {
                String dateStr = longToString(timeStamp, formatTimeStr);
                return getWeek(dateStr, formatTimeStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param dateStr
     * @return
     * @desc 显示某个时间戳是今天、昨天、前天或星期几
     */
    public static String getWeekOrTodayORYesterdayOrBeforeday(Context context, String dateStr, String formatTimeStr) {

        try {
            if (isToday(stringToLong(dateStr, formatTimeStr))) {

                return context.getResources().getString(R.string.Today);
            } else if (isYesterday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.yesterday);
            } else if (isBeforeday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.beforeyesterday);
            } else {
                return getWeek(dateStr, formatTimeStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param dateStr
     * @return
     * @desc 显示某个时间戳是今天、昨天、前天或某个日期字符串
     */
    public static String getDateStrOrTodayORYesterdayOrBeforeday(Context context, String dateStr, String formatTimeStr) {

        try {
            if (isToday(stringToLong(dateStr, formatTimeStr))) {

                return context.getResources().getString(R.string.Today);
            } else if (isYesterday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.yesterday);
            } else if (isBeforeday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.beforeyesterday);
            } else {
                return dateStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param dateStr
     * @return
     * @desc 显示某个时间戳是今天、昨天、前天或某个日期字符串
     */
    public static String getDateStrOrTodayORYesterdayOrBeforeday(Context context, String dateStr, String formatTimeStr, String otherFormatTimeStr) {

        try {
            if (isToday(stringToLong(dateStr, formatTimeStr))) {

                return context.getResources().getString(R.string.Today);
            } else if (isYesterday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.yesterday);
            } else if (isBeforeday(stringToLong(dateStr, formatTimeStr))) {
                return context.getResources().getString(R.string.beforeyesterday);
            } else {
                String otherDateStr = getOtherDateStr(dateStr, formatTimeStr, otherFormatTimeStr);
                return otherDateStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getWeek(String dateStr, String formatTimeStr) {
        if (MultiLanguageUtil.getInstance().isZh()) {
            return DateUtils.getChineseWeek(dateStr, formatTimeStr);
        } else {
            return DateUtils.getEngLishWeek(dateStr, formatTimeStr);
        }
    }

    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * @param currentTime 要转换的long类型的时间
     * @param formatType  要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * @param data       Date类型的时间
     * @param formatType 格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
}


package cap.ljw.util;

import cn.hutool.core.date.DateUtil;

/**
 * @author Ljw
 * 2023.7.11
 * 日志工具类
 */
public class DateTimeUtil {
//获取月份的日期字符串格式以数组形式[]
public static String[] getMonthDayList(String month){
    int monthSize= DateUtil.lengthOfMonth(Integer.valueOf(month.substring(4)),DateUtil.isLeapYear(Integer.valueOf(month.substring(0,4))));
    String[] monthDayList=new String[monthSize];
    for (int i = 0; i <monthSize ; i++) {
        if (i<9){
            monthDayList[i]=month+"0"+(i+1);
        }else {
            monthDayList[i]=month+(i+1);
        }
    }
    return monthDayList;
}





}

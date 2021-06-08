package com.vueblog.untils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-07-15-39
 */
public class DateUntils {
    public static long days = 0;

    /**
     * 天数间隔
     * @param startDate
     * @param endDate
     * @return
     */
    public static long dayInterval(String startDate, String endDate){
        //创建时间格式对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //转换时间Date类型
            Date startTime = simpleDateFormat.parse(startDate);
            Date endTime = simpleDateFormat.parse(endDate);
            //获取时间的毫秒数
            long start = startTime.getTime();
            long end = endTime.getTime();
            //计算间隔的天数
            days = (end - start)/(60*60*24*1000);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }
}

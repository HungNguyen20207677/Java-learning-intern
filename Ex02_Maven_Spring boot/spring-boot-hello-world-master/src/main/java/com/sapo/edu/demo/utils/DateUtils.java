package com.sapo.edu.demo.utils;

import java.util.Date;

/**
 * Lớp tiện ích cho các thao tác trên các đối tượng Date.
 */
public class DateUtils {

    /**
     * Thêm một số tuần vào một ngày đã cho.
     *
     * @param date ngày cần thêm tuần
     * @param weeks số tuần cần thêm
     * @return một đối tượng Date mới đại diện cho ngày sau khi đã thêm tuần
     */
    public static Date addWeeksToDate(Date date, int weeks) {
        // Giao việc thêm tuần cho đối tượng Date cho thư viện Apache Commons Lang
        return org.apache.commons.lang3.time.DateUtils.addWeeks(date, weeks);
    }
}

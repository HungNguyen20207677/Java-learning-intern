package com.sapo.edu.demo.utils;

/**
 * Lớp tiện ích cho các thao tác xử lý văn bản.
 */
public class StringUtils {

    /**
     * Loại bỏ khoảng trắng từ đầu và cuối chuỗi văn bản.
     *
     * @param text chuỗi văn bản cần được loại bỏ khoảng trắng
     * @return chuỗi văn bản đã được loại bỏ khoảng trắng ở hai đầu
     */
    public static String trimText(String text) {
        // Sử dụng Apache Commons Lang để loại bỏ khoảng trắng
        return org.apache.commons.lang3.StringUtils.trim(text);
    }

    /**
     * Đảo ngược chuỗi văn bản.
     *
     * @param text chuỗi văn bản cần được đảo ngược
     * @return chuỗi văn bản đã được đảo ngược
     */
    public static String reverseText(String text) {
        // Sử dụng Apache Commons Lang để đảo ngược chuỗi
        return org.apache.commons.lang3.StringUtils.reverse(text);
    }
}

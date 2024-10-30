package com.sapo.edu.demo.utils;

/**
 * Lớp tiện ích cho các thao tác trên mảng.
 */
public class ArrayUtils {

    /**
     * Kiểm tra xem mảng số nguyên đã chỉ định có chứa giá trị đã cho hay không.
     *
     * @param array mảng số nguyên để kiểm tra
     * @param value giá trị cần tìm kiếm
     * @return true nếu mảng chứa giá trị, ngược lại trả về false
     */
    public static boolean contains(int[] array, int value) {
        // Giao việc kiểm tra có chứa giá trị cho thư viện Apache Commons Lang
        return org.apache.commons.lang3.ArrayUtils.contains(array, value);
    }

    /**
     * Đảo ngược mảng số nguyên đã chỉ định.
     *
     * @param array mảng số nguyên để đảo ngược
     * @return một mảng mới chứa các phần tử của mảng ban đầu theo thứ tự ngược lại
     */
    public static int[] reverseArray(int[] array) {
        // Giao việc đảo ngược mảng cho thư viện Apache Commons Lang
        return org.apache.commons.lang3.ArrayUtils.clone(array);
    }
}

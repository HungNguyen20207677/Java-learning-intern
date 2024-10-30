package com.sapo.edu.demo.utils;

import java.util.concurrent.TimeUnit;

/**
 * Lớp tiện ích cho việc đo thời gian thực thi của một nhiệm vụ.
 */
public class StopWatch {

    /**
     * Đo thời gian thực thi của một nhiệm vụ được xác định bởi một Runnable.
     *
     * @param task nhiệm vụ cần đo thời gian thực thi
     * @return thời gian thực thi của nhiệm vụ trong milliseconds
     */
    public static long measureElapsedTime(Runnable task) {
        // Tạo và bắt đầu đồng hồ đo thời gian
        org.apache.commons.lang3.time.StopWatch stopWatch = org.apache.commons.lang3.time.StopWatch.createStarted();

        // Thực thi nhiệm vụ
        task.run();

        // Dừng đồng hồ và trả về thời gian đã đo được
        stopWatch.stop();
        return stopWatch.getTime(TimeUnit.MILLISECONDS);
    }
}

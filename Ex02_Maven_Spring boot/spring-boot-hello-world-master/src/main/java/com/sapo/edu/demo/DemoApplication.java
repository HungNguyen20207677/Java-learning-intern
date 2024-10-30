package com.sapo.edu.demo;

import com.sapo.edu.demo.model.InputData;
import com.sapo.edu.demo.utils.ArrayUtils;
import com.sapo.edu.demo.utils.DateUtils;
import com.sapo.edu.demo.utils.StopWatch;
import com.sapo.edu.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Ví dụ sử dụng
		InputData inputData = new InputData();
		inputData.setText("  Apache Commons Lang tuyệt vời  ");
		inputData.setNumbers(new int[]{1, 2, 3, 4, 5});


		// Đo thời gian thực hiện
		long elapsedTime = StopWatch.measureElapsedTime(() -> {
			// Task
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// Xử lý ngoại lệ khi bị gián đoạn
			}
		});
		System.out.println("Thời gian đã trôi qua: " + elapsedTime + " mili giây");

		// Cắt bỏ khoảng trắng của văn bản
		try {
			String trimmedText = StringUtils.trimText(inputData.getText());
			System.out.println("Văn bản đã cắt bỏ khoảng trắng: " + trimmedText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Đảo ngược chuỗi
		try {
			String reversedText = StringUtils.reverseText(inputData.getText());
			System.out.println("Văn bản đã đảo ngược: " + reversedText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Kiểm tra mảng có chứa một số hay không
		try {
			boolean containsNumber = ArrayUtils.contains(inputData.getNumbers(), 3);
			System.out.println("Mảng có chứa số 3: " + containsNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Đảo ngược mảng
		try {
			int[] reversedArray = ArrayUtils.reverseArray(inputData.getNumbers());
			System.out.println("Mảng đã đảo ngược: " + org.apache.commons.lang3.ArrayUtils.toString(reversedArray));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Lấy ngày hôm nay và ngày sau một tuần
		try {
			Date today = new Date();
			Date nextWeek = DateUtils.addWeeksToDate(today, 1);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Hôm nay: " + dateFormat.format(today));
			System.out.println("Tuần sau: " + dateFormat.format(nextWeek));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

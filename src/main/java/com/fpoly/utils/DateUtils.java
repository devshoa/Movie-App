package com.fpoly.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	public static void main(String[] args) {
		String a = GetDay("2023-04-01 00:00:00.0");
		System.out.println(a);
	}

	public static String GetDay(String storyTimestamp) {
		// Thời điểm hiện tại
		LocalDateTime currentTime = LocalDateTime.now();

		// Thời điểm đăng story có định dạng "2023-04-01 00:00:00.0"
//		String storyTimestamp = "2023-04-01 00:00:00.0";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime storyTime = LocalDateTime.parse(storyTimestamp, formatter);

		// Tính thời gian chênh lệch
		Duration duration = Duration.between(storyTime, currentTime);

		// Tính số giờ và ngày chênh lệch
		long hoursDifference = duration.toHours();
		long daysDifference = duration.toDays();

		// Định dạng và hiển thị thời gian chênh lệch
		if (hoursDifference < 24) {
			return +hoursDifference + " giờ trước";
		} else {
			return daysDifference + " ngày trước";
		}
	}

	public static String getDateNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = new Date();
		return dateFormat.format(date);
	}
}

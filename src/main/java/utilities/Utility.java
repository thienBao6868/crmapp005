package utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public String ConvertDateTimeToTimestamp(String day) {
		 // Định dạng của chuỗi ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate date = LocalDate.parse(day, formatter);

        // Chuyển đổi LocalDate thành LocalDateTime với thời gian mặc định
        LocalDateTime dateTime = date.atStartOfDay();

        // Định dạng của chuỗi timestamp
        DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Chuyển đổi LocalDateTime thành chuỗi timestamp
        String timestampString = dateTime.format(timestampFormatter);

        return timestampString;
	}
	
	public String convertTimestampToDateTime (String day) {
		// Định dạng cho chuỗi đại diện cho timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(day, formatter);

        // Định dạng lại LocalDateTime thành chuỗi theo định dạng mới
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String formattedDateTime = dateTime.format(newFormatter);
        
        return formattedDateTime;

	}

}


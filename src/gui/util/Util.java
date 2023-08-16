package gui.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class Util {
	
	public static Integer valorInteiro (String txt) {
		try {
			return Integer.parseInt(txt);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void formatDatePicker (DatePicker dp, String forma) {
		dp.setConverter(new StringConverter<LocalDate>() {
			
			DateTimeFormatter dataFormata = DateTimeFormatter.ofPattern(forma);
			{
				dp.setPromptText(forma.toLowerCase());
			}
			
			@Override
			public String toString(LocalDate dt) {
				if(dt != null) {
					return dataFormata.format(dt);
				}else {
					return "";
				}				
			}
			
			@Override
			public LocalDate fromString(String data) {
				if (data != null && !data.isEmpty()) {
					return LocalDate.parse(data, dataFormata);
				}else {
					return null;
				}
			}
		});
	}
}

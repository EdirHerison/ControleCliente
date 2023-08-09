package gui.util;

public class Util {
	
	public static Integer valorInteiro (String txt) {
		try {
			return Integer.parseInt(txt);
		} catch (Exception e) {
			return null;
		}
	}

}

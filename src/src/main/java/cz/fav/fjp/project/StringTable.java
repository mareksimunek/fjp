package cz.fav.fjp.project;

import java.util.HashMap;
import java.util.Map;

public class StringTable {

	public static Map<String, String> codeToString = new HashMap<String, String>();
	public static Map<String, String> stringToCode = new HashMap<String, String>();
	
	private static int count = 0;
	
	public static String getCode(String string) {
		String ret = stringToCode.get(string);
		if (ret == null) {
			String code = "#STRING_" + (count++);
			stringToCode.put(string, code);
			codeToString.put(code, string);
			ret = code;
		}
		
		return ret;
	}
	
	public static String getString(String code) {
		return codeToString.get(code);
	}
	
	
}

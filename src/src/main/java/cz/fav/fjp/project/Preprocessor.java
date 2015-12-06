package cz.fav.fjp.project;

import java.util.ArrayList;
import java.util.List;

public class Preprocessor {

	public static List<String> removeComments(List<String> list) {
		ArrayList<String> ret = new ArrayList<String>();
		boolean inComment = false;

		for (String line : list) {

			if (inComment) {
				if (line.split("\\*/").length > 1) {
					ret.add(line.split("\\*/")[1]);
					inComment = false;
				}
			} else {
				line = line.replaceAll("/\\*.*\\*/", "");
				line = line.replaceAll("//.*", "");
				if (line.split("/\\*").length > 1) {
					ret.add(line.split("/\\*")[0]);
					inComment = true;
				}
				else {
					ret.add(line);
				}
			}
		}
		return ret;
	}

	public static List<String> removeImports(List<String> list) {
		ArrayList<String> ret = new ArrayList<String>();

		for (String line : list) {
			if (line.startsWith("import")) continue;
			if (line.startsWith("package")) continue;
			ret.add(line);
		}
		
		return ret;
	}

	public static List<String> removeEmptySpaces(List<String> list) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String line : list) {
			line = line.replace("\t", "");
			if (line.trim().length() > 0) ret.add(line);
		}
		return ret;
	}

	public static List<String> removeStrings(List<String> source) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String line : source) {
			String rep = replaceStringsInOneLine(line, '"'); 
			rep = replaceStringsInOneLine(rep, '\''); 
			ret.add(rep);
		}
		return ret;
	}

	private static String replaceStringsInOneLine(String line, char delimiter) {
		ArrayList<String> foundStrings = new ArrayList<String>();
		boolean inString = false;
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < line.length(); i++) {
			if (inString) {
				sb.append(line.charAt(i));
				if (line.charAt(i) == delimiter) {
					inString = false;
					foundStrings.add(sb.toString());
				}
			}
			else {
				if (line.charAt(i) == delimiter) {
					inString = true;
					sb.append(delimiter);
				}
			}
		}
		
		for (String string : foundStrings) {
			line = line.replace(string, StringTable.getCode(string));
		}
		
		return line;
	}
	
	public static List<String> getWords(List<String> source) {
		ArrayList<String> ret = new ArrayList<String>();
		
		for (String line : source) {
			ret.addAll(getWordsFromLine(line));
			//ret.add("\n");
		}
		
		return ret;
	}
	
	private static List<String> getWordsFromLine(String line) {
		
		List<String> words = new ArrayList<String>();
		String[] multiChars = {"<<=", ">>=", "==", "<=", ">=", "!=", "\\+\\+", "--", "\\|="};
		
		for (String multichar : multiChars) {
			if (line.split(multichar).length > 1) {
				//System.out.println(multichar);
				String[] linePart = line.split(multichar);
				for (int i=0; i<linePart.length-1; i++) {
					words.addAll(getWordsFromLine(linePart[i]));
					words.add(multichar.replace("\\", ""));
				}
				words.addAll(getWordsFromLine(linePart[linePart.length-1]));
				return words;
			}
		}
		
		String[] singleChars = { "=", "<", ">", ";", "\\+", "\\-", "\\(", "\\)", "\\[", "\\]", "\\{", "\\}", "\\." };
		for (String singleChar : singleChars) {
			line = line.replaceAll(singleChar, " " + singleChar + " ");
		}
		
		for (String word : line.split(" ")) {
			if (word.trim().length() > 0) words.add(word);
		}
		
		return words;
	}
	
}

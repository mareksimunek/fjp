package cz.fav.fjp.project.logger;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Logger {

	public static OutputStreamWriter output = new OutputStreamWriter(System.out);
	public static int logLevel = 0;
	
	public static void log(String s, int level)  {
		if (level <= logLevel)	{
			try {
				output.write("[LOG]: " + s + System.lineSeparator());
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setLogLevel(int level) {
		logLevel = level;
	}
	
	public static void setLogOutput(OutputStreamWriter osw) {
		output = osw;
	}
	
}

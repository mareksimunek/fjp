package cz.fav.fjp.project.writter;

import java.io.OutputStreamWriter;

public class WritterSettings {

	public static OutputStreamWriter DEF_OUT_STREAMWRITTER = new OutputStreamWriter(System.out);
	
	public static OutputStreamWriter DEF_OUT_LOG = DEF_OUT_STREAMWRITTER;
	
	public static int LOG_LEVEL = 0;
	

}

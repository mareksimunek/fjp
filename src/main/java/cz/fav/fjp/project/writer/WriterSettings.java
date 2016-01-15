package cz.fav.fjp.project.writer;

import java.io.OutputStreamWriter;

public class WriterSettings {

	public static OutputStreamWriter DEF_OUT_STREAMWRITER = new OutputStreamWriter(System.out);
	
	public static OutputStreamWriter DEF_OUT_LOG = DEF_OUT_STREAMWRITER;
	
	public static int LOG_LEVEL = 8;
	
	

}

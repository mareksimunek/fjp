package cz.fav.fjp.project.writter;

import java.io.IOException;
import java.io.OutputStreamWriter;

import cz.fav.fjp.project.objects.ParsableObject;

public abstract class DefaultWritter<T> {

	protected OutputStreamWriter writter;
	
	public DefaultWritter(OutputStreamWriter wr) {
		this.writter = wr;
	}
	
	public DefaultWritter() {
		this(WritterSettings.DEF_OUT_STREAMWRITTER);
	}
	
	protected void write(String s) {
		try {
			this.writter.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeln(String s) {
		this.write(s + System.lineSeparator());
	}
	
	protected void writeln() {
		this.write(System.lineSeparator());
	}
	
	protected void log(String s, int level)  {
		if (level <= WritterSettings.LOG_LEVEL)	{
			try {
				for (int i=0; i<level*2; i++) WritterSettings.DEF_OUT_LOG.write(" ");
				WritterSettings.DEF_OUT_LOG.write("[LOG]: " + s + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	abstract public void transform(T obj);
	
}

package cz.fav.fjp.project.writer;

import java.io.IOException;
import java.io.OutputStreamWriter;

import cz.fav.fjp.project.StringTable;

public abstract class DefaultWriter<T> {

	protected OutputStreamWriter writer;
	
	public DefaultWriter(OutputStreamWriter wr) {
		this.writer = wr;
	}
	
	public DefaultWriter() {
		this(WriterSettings.DEF_OUT_STREAMWRITER);
	}
	
	protected void write(String s) {
		try {
			String res = new String(s);
			for (String code: StringTable.codeToString.keySet()) {
				res = res.replace(code, StringTable.getString(code));
			}
			this.writer.write(res);
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
		if (level <= WriterSettings.LOG_LEVEL)	{
			try {
				for (int i=0; i<level*2; i++) WriterSettings.DEF_OUT_LOG.write(" ");
				WriterSettings.DEF_OUT_LOG.write("[LOG]: " + s + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	abstract public void transform(T obj);
	
}

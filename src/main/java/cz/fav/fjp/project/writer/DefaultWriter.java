package cz.fav.fjp.project.writer;

import java.io.IOException;
import java.io.OutputStreamWriter;

import cz.fav.fjp.project.StringTable;
import cz.fav.fjp.project.writer.commands.SystemWriter;

public abstract class DefaultWriter<T> {

	protected OutputStreamWriter writer;
	
	public DefaultWriter(OutputStreamWriter wr) {
		this.writer = wr;
	}
	
	public DefaultWriter() {
		this(WriterSettings.output);
	}
	
	protected void write(String s) {
		try {
			String res = new String(s);
			String newCode = null;
			for (String code: StringTable.codeToString.keySet()) {
				newCode = StringTable.getString(code);
				if(this instanceof SystemWriter){
					newCode = newCode.substring(1, newCode.length() - 1); //get rid of " in printf
				}
					res = res.replace(code, newCode);						
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
	
	abstract public void transform(T obj) throws Exception ;
	
}

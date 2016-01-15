package cz.fav.fjp.project.writer;

import java.io.IOException;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FClass;
import cz.fav.fjp.project.objects.FCompilationUnit;

public class CompilationUnitWriter extends DefaultWriter<FCompilationUnit> {

	@Override
	public void transform(FCompilationUnit obj) throws Exception {
		
		Logger.log("Writing compilation unit: " + obj.getName(), 1);
		
		writeln("#include <stdio.h>");
		writeln("#include <stdlib.h>");
		writeln("#include <math.h>");
		writeln("#include <string.h>");
		writeln();
		writeln("#define STR_LEN 256");
		writeln();
		writeln("char str[STR_LEN];");
		writeln();
		writeln("char* readLine() {");
		writeln("  fgets(str, STR_LEN, stdin);");
		writeln("  str[strlen(str) - 1] = '\\0';");
		writeln("  return str;");
		writeln("}");
		writeln();
		
		for (FClass c : obj.getClasses()) {
			ClassWriter classWriter = new ClassWriter();
			classWriter.transform(c);
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
}

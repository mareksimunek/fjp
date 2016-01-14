package cz.fav.fjp.project.writter;

import java.io.IOException;

import cz.fav.fjp.project.objects.FCompilationUnit;

public class CompilationUnitWritter extends DefaultWritter<FCompilationUnit> {

	@Override
	public void transform(FCompilationUnit obj) {
		
		log("Writing compilation unit: " + obj.getName(), 1);
		
		writeln("#include <stdio.h>");
		writeln("#include <stdlib.h>");
		writeln("#include <math.h>");
		writeln("#include <string.h>");
		writeln();
		writeln("#define STR_LEN 256");
		writeln();
		writeln("char* readLine() {");
		writeln("  char str[STR_LEN];");
		writeln("  fgets(str, STR_LEN, stdin);");
		writeln("  str[strlen(str) - 1] = '\\0';");
		writeln("  return str;");
		writeln("}");
		writeln();
		
		obj.getClasses().forEach( c -> {
			ClassWritter classWritter = new ClassWritter();
			classWritter.transform(c);
		});
		
		
		
		try {
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
}

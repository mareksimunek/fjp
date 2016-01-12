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

package cz.fav.fjp.project;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.fav.fjp.project.objects.FCompilationUnit;
import cz.fav.fjp.project.writter.CompilationUnitWritter;
import cz.fav.fjp.project.writter.WritterSettings;


public class Main {
	
	public static void main(String[] args) throws Exception {
		List<String> source = loadFromFile(args);
		source = Preprocessor.removeComments(source);
		source = Preprocessor.removeStrings(source);
		source = Preprocessor.removeImports(source);
		source = Preprocessor.removeEmptySpaces(source);
		
		List<String> words = Preprocessor.getWords(source);
		
		FCompilationUnit unit = new FCompilationUnit();
		unit.setWords(words);
		unit.setName("DekodovaniPasky");
		unit.parse();
		/*
		unit.getClasses().get(0).getMethods().forEach( m -> {
			System.out.print(m.getName() + " - args: " + m.getArguments().toString() + ", return: ");
			System.out.println(m.getReturnValueType());
		});
		*/
		
		System.out.println();
		System.out.println();
		
		CompilationUnitWritter wr = new CompilationUnitWritter();
		wr.transform(unit);
	}
	
	private static List<String> loadFromFile(String[] args) throws IOException {
		
		//String path = "/home/jpouba/Downloads/PilsProg/DekodovaniPasky.java";
        //String path = "C:\\fjp-2\\PilsProg\\DekodovaniPasky.java";

		//String path = "D:\\STAZENO\\PilsProg\\PilsProg\\DekodovaniPasky.java";
		String path = args[0];
		return FileUtils.readLines(new File(path));
	}

}

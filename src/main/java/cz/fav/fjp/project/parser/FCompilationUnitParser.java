package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.enums.KeyWords;
import cz.fav.fjp.project.objects.FClass;
import cz.fav.fjp.project.objects.FCompilationUnit;

public class FCompilationUnitParser {

	public static void parse(FCompilationUnit compilationUnit) throws Exception {
		// only classes allowed here
		List<String> words = compilationUnit.getWords();
		
		FClass actClass = new FClass();
		
		int i=-1;
		while (true) {
			i++;
			if (i >= words.size()) break;
			
			i = Processor.getModifiers(words, actClass.getModifiers(), i);
			
			String word = words.get(i);
			if (word.equals(KeyWords.kwClass)) {
				i++;
				actClass.setName(words.get(i));
				i++;
				List<String> classContent = new ArrayList<String>();
				i = Processor.getContentInsideBrackets(words, classContent, i, "{", "}");
				actClass.setWords(classContent);
				actClass.parse();
				compilationUnit.getClasses().add(actClass);
				actClass = new FClass();
			}
			
		}
	}
	
	
	
}

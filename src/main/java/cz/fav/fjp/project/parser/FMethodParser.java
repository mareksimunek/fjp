package cz.fav.fjp.project.parser;

import java.util.List;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FMethod;

public class FMethodParser {

	public static void parse(FMethod methodToParse) throws Exception {
		List<String> words = methodToParse.getWords();
		
		System.out.println("Parsing method '" + methodToParse.getName() + "'");
		
		Utils.prettyPrint(words);
		
		List<FCommand> commandList = CommandBlockParser.parseBlock(words, methodToParse);
		methodToParse.setCommnands(commandList);
	}
	
}
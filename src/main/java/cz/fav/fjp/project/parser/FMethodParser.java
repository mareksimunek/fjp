package cz.fav.fjp.project.parser;

import java.util.List;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FMethod;

public class FMethodParser {

	public static void parse(FMethod methodToParse) throws Exception {
		List<String> words = methodToParse.getWords();
		
		Logger.log("Parsing method '" + methodToParse.getName() + "'", 2);
		
		List<FCommand> commandList = CommandBlockParser.parseBlock(words, methodToParse);
		methodToParse.setCommnands(commandList);
	}
	
}
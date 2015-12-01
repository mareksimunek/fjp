package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FAttribute;
import cz.fav.fjp.project.objects.FClass;
import cz.fav.fjp.project.objects.FMethod;

public class FClassParser {

	public static void parse(FClass classToParse) throws Exception {
		List<String> words = classToParse.getWords();
//		System.out.println("Parsing class '" + classToParse.getName() + "'");
		
		List<String> modifiers = new ArrayList<String>();
		String returnType, name, initValue;
		
		int i=-1;
		while (true) {
			i++;
			if (i >= words.size()) break;
			
			i = Processor.getModifiers(words, modifiers, i);
			
			String word = words.get(i);
			returnType = word;
			i++;
			name = words.get(i);
			i++;
			
			if (words.get(i).equals("(")) {
				List<String> arguments = new ArrayList<String>();
				List<String> methodBody = new ArrayList<String>();
				i = Processor.getContentInsideBrackets(words, arguments, i, "(", ")");
				i = Processor.getContentInsideBrackets(words, methodBody, i, "{", "}");
				FMethod method = new FMethod();
				method.setName(name);
				method.setReturnValueType(returnType);
				method.setModifiers(modifiers);
				method.setArguments(arguments);
				method.setWords(methodBody);
				method.parse();
				classToParse.getMethods().add(method);
			}
			else if (words.get(i).equals("=")) {
				//TODO
				i++;
				initValue = words.get(i);
				i++;
				FAttribute attr = new FAttribute();
				attr.setInitialValue(initValue);
				attr.setName(name);
				attr.setType(returnType);
				attr.setModifiers(modifiers);
				classToParse.getAttributes().add(attr);
			}
			else if (words.get(i).equals(";")) {
				FAttribute attr = new FAttribute();
				attr.setName(name);
				attr.setType(returnType);
				attr.setModifiers(modifiers);
				classToParse.getAttributes().add(attr);
			}
			
			
		}
	}
	
}
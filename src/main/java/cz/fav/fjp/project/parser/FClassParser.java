package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.*;

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
				FMethod method = new FMethod(classToParse);
				method.setName(name);
				FVarType rv = new FVarType();
				rv.setValue(returnType);
				method.setReturnValueType(rv);
				method.setModifiers(modifiers);
				method.setArguments(parseArgs(arguments, method));
				method.setWords(methodBody);
				method.parse();
				classToParse.getMethods().add(method);
			}
			else if (words.get(i).equals("=")) {
				//TODO
				i++;
				initValue = words.get(i);
				i++;
				FAttribute attr = new FAttribute(classToParse);
				attr.setInitialValue(initValue);
				FVariable variable = new FVariable(classToParse);
				variable.setName(name);
				FVarType type = new FVarType();
				type.setValue(returnType);
				variable.setType(type);
				attr.setVariable(variable);
				attr.setModifiers(modifiers);
				classToParse.getAttributes().add(attr);
			}
			else if (words.get(i).equals(";")) {
				FAttribute attr = new FAttribute(classToParse);
				FVariable variable = new FVariable(classToParse);
				variable.setName(name);
				FVarType type = new FVarType();
				type.setValue(returnType);
				variable.setType(type);
				attr.setVariable(variable);
				attr.setModifiers(modifiers);
				classToParse.getAttributes().add(attr);
			}
			
			
		}
	}
	
	private static List<FVariable> parseArgs(List<String> args, ParentClass parent) throws Exception {
		List<FVariable> ret = new ArrayList<FVariable>();
		int i=0;
		while (i < args.size()) {
			FVariable variable = new FVariable(parent);
			String type = args.get(i);
			i++;
			if (args.get(i).equals("[")) {
				type += "[]";
				i +=2;
			}
			variable.setName(args.get(i));
			FVarType vt = new FVarType();
			vt.setValue(type);
			variable.setType(vt);
			i++;
			ret.add(variable);
		}
		return ret;
	}
	
}
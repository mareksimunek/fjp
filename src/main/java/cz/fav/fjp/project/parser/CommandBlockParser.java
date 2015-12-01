package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.enums.KeyWords;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.commands.FExpression;
import cz.fav.fjp.project.objects.commands.FIf;

public class CommandBlockParser {

	public static List<FCommand> parseBlock(List<String> words) throws Exception {
		ArrayList<FCommand> ret = new ArrayList<FCommand>();
		
		int i=0;
		while (true) {
			if (i >= words.size()) break;
			
			List<String> nCommand = new ArrayList<String>();
			i = getSingleCommand(words, nCommand, i);
			FCommand newCommand = parseSingleCommand(nCommand);
			ret.add(newCommand);
			
			//System.out.println("Ncom: " + nCommand.toString());
			
		}
		return ret;
	}

	public static FCommand parseSingleCommand(List<String> command) throws Exception {
		int i = 0;
		
		System.out.println("Parsing single command: " + command.toString());
		
		/*
		if (command.get(i).equals(KeyWords.kwIf)) {
			i++;
			List<String> expr = new ArrayList<String>();
			FExpression fexpr = new FExpression();
			i = Processor.getContentInsideBrackets(command, expr, i, "(", ")");
			fexpr.setWords(expr);
			fexpr.parse();
			
			List<String> cmds = new ArrayList<String>();
			if (!command.get(i).equals(("{"))) {
				cmds.add(command.get(i));
				i++;
			}
			else {
				i = Processor.getContentInsideBrackets(command, cmds, i, "{", "}");
			}
			FIf foundIf = new FIf();
			foundIf.setWords(cmds);
			foundIf.setCondition(fexpr);
		}
		*/
		
		return null;
	}
	
	public static int getSingleCommand(List<String> words, List<String> output, int position) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		int i = position;
		
		if (words.get(i).equals(KeyWords.kwIf)) {
			i++;
			i = Processor.getContentInsideBrackets(words, list, i, "(", ")");
			output.add("if");
			output.add("(");
			output.addAll(list);
			output.add(")");
			if (!words.get(i).equals(("{"))) {
				ArrayList<String> singleCmd = new ArrayList<String>();
				i = getSingleCommand(words, singleCmd, i);
				output.addAll(singleCmd);
			}
			else {
				ArrayList<String> blockCmd = new ArrayList<String>();
				i = Processor.getContentInsideBrackets(words, blockCmd, i, "{", "}");
				output.add("{");
				output.addAll(blockCmd);
				output.add("}");
			}
		} 
		else if (words.get(i).equals(KeyWords.kwWhile)) {
			i++;
			i = Processor.getContentInsideBrackets(words, list, i, "(", ")");
			output.add("while");
			output.add("(");
			output.addAll(list);
			output.add(")");
			if (!words.get(i).equals(("{"))) {
				ArrayList<String> singleCmd = new ArrayList<String>();
				i = getSingleCommand(words, singleCmd, i);
				output.addAll(singleCmd);
			}
			else {
				ArrayList<String> blockCmd = new ArrayList<String>();
				i = Processor.getContentInsideBrackets(words, blockCmd, i, "{", "}");
				output.add("{");
				output.addAll(blockCmd);
				output.add("}");
			}
		} 
		else if (words.get(i).equals(KeyWords.kwFor)) {
			i++;
			i = Processor.getContentInsideBrackets(words, list, i, "(", ")");
			output.add("while");
			output.add("(");
			output.addAll(list);
			output.add(")");
			if (!words.get(i).equals(("{"))) {
				ArrayList<String> singleCmd = new ArrayList<String>();
				i = getSingleCommand(words, singleCmd, i);
				output.addAll(singleCmd);
			}
			else {
				ArrayList<String> blockCmd = new ArrayList<String>();
				i = Processor.getContentInsideBrackets(words, blockCmd, i, "{", "}");
				output.add("{");
				output.addAll(blockCmd);
				output.add("}");
			}
		} 
		else {
			String word = words.get(i);
			while (!word.equals(";")) {
				output.add(word);
				i++;
				word = words.get(i);
			}
			output.add(word);
			i++;
		}
		
		
		return i;
	}

	
	
}

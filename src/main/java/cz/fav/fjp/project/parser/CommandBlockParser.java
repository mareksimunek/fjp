package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.enums.KeyWords;
import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.objects.commands.FMethodCall;
import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.objects.commands.FWhile;

public class CommandBlockParser {

	public static List<FCommand> parseBlock(List<String> words, ParentClass parent) throws Exception {
		ArrayList<FCommand> ret = new ArrayList<FCommand>();
		
		int i=0;
		while (true) {
			if (i >= words.size()) break;
			
			List<String> nCommand = new ArrayList<String>();
			i = getSingleCommand(words, nCommand, i);
			FCommand newCommand = parseSingleCommand(nCommand, parent);
			ret.add(newCommand);
		}
		return ret;
	}

	public static FCommand parseSingleCommand(List<String> command, ParentClass parent) throws Exception {
		int i = 0;
		
		Logger.log("** Parsing single command: " + command.toString(), 3);
		
		if (command.get(i).equals(KeyWords.kwIf)) {
			FIf fif = new FIf(parent);
			fif.setWords(command);
			fif.parse();
			return fif;
		}
		else if (command.get(i).equals(KeyWords.kwWhile)) {
			FWhile fwhile = new FWhile(parent);
			fwhile.setWords(command);
			fwhile.parse();
			return fwhile;
		}
		else if (command.get(i).equals(KeyWords.kwFor)) {
			FFor ffor = new FFor(parent);
			ffor.setWords(command);
			ffor.parse();
			return ffor;
		}
		else if (command.get(i).equals(KeyWords.kwReturn)) {
			FReturn freturn = new FReturn(parent);
			freturn.setWords(command);
			freturn.parse();
			return freturn;
		}
        else if (command.get(i).equals(KeyWords.kwSystem)) {
			FSystem fsystem = new FSystem(parent);
			fsystem.setWords(command);
			fsystem.parse();
			return fsystem;
		}
		else if (command.size() == 3) {
				FVarDeclaration decl = new FVarDeclaration(parent);
				decl.setWords(command);
				decl.parse();
				return decl;
		}
		else if (command.stream().filter(s -> s.contains("=")).count() == 1) {
			List<String> toWhere = new ArrayList<String>();
			int j=0;
			while (!command.get(j).contains("=")) toWhere.add(command.get(j++));
			
			if (toWhere.size() == 1) {
				FAssignment fassignment = new FAssignment(parent);
				fassignment.setWords(command);
				fassignment.parse();
				return fassignment;
			}
			else if (toWhere.size() == 2) { 
				FVarDeclarationWithInitialization fdecl = new FVarDeclarationWithInitialization(parent);
				fdecl.setWords(command);
				fdecl.parse();
				return fdecl;
			}
			else {
				Logger.log("Weird assingment: " + command.toString(), 2);
			}
			
			
			
		}
		else if (command.get(command.size() - 2).equals(")")) {
			FMethodCall fmethodcall = new FMethodCall(parent);
			fmethodcall.setWords(command);
			fmethodcall.parse();
			return fmethodcall;
		}
		else {
			Logger.log(" * * * * Unrecognized command: " + command.toString(), 2);
		}
		
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
				
				if (i < words.size()) {
					if (words.get(i).equals("else")) {
						i++;
						
						if (!words.get(i).equals(("{"))) {
							ArrayList<String> singleCmd = new ArrayList<String>();
							i = getSingleCommand(words, singleCmd, i);
							output.addAll(singleCmd);
						}
						else {
							ArrayList<String> blockCmdElse = new ArrayList<String>();
							i = Processor.getContentInsideBrackets(words, blockCmdElse, i, "{", "}");
							output.add("{");
							output.addAll(blockCmdElse);
							output.add("}");
						}
						
					}
				}
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
			output.add("for");
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

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.*;
import cz.fav.fjp.project.parser.CommandBlockParser;
import cz.fav.fjp.project.parser.Processor;

public class FWhile extends FCommand implements ParentClass, ObjectWithLocalVars {

	private FExpression condition;
	private List<FCommand> commands = new ArrayList<FCommand>();
	private Map<String, FVarType> variablesTable = new HashMap<>();

	private ParentClass parent;

	public FWhile(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing while.");
		
		int i=1;
		FExpression fexpr = new FExpression(this);
		List<FCommand> fcmds  = new ArrayList<FCommand>();
		
		List<String> expr = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), expr, i, "(", ")");
		fexpr.setWords(expr);
		fexpr.parse();
		
		List<String> cmds = new ArrayList<String>();
		if (!getWords().get(i).equals(("{"))) {
			CommandBlockParser.getSingleCommand(getWords(), cmds, i);
			FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds, this);
			fcmds.add(singleCmd);
		}
		else {
			i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
			fcmds.addAll(CommandBlockParser.parseBlock(cmds, this));
		}
		
		this.setCondition(fexpr);
		this.setCommands(fcmds);
	}
	
	public FExpression getCondition() {
		return condition;
	}
	
	public void setCondition(FExpression condition) {
		this.condition = condition;
	}
	
	public List<FCommand> getCommands() {
		return commands;
	}
	
	public void setCommands(List<FCommand> commands) {
		this.commands = commands;
	}

	public ParentClass getParent() {
		return parent;
	}

	@Override
	public boolean addVarToTable(FVariable variable) {
		boolean result = Utils.addVarToTable(this.variablesTable, variable);
		System.out.println("While: Iterating through variables table:");
		for ( Map.Entry<String, FVarType> entry : this.variablesTable.entrySet() ) {
			String key = entry.getKey();
			FVarType value = entry.getValue();
			System.out.println(value.getValue() + " " + key);
		}
		System.out.println("");
		return result;
	}
}

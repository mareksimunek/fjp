package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.*;
import cz.fav.fjp.project.parser.CommandBlockParser;
import cz.fav.fjp.project.parser.Processor;

public class FFor extends FCommand implements ParentClass, ObjectWithLocalVars {

	private FExpression condition;
	private FCommand decl;
	private FCommand increment;
	private List<FCommand> commands = new ArrayList<FCommand>();
	private Map<String, FVarType> variablesTable = new HashMap<>();

	private ParentClass parent;

	public FFor(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		Logger.log("Parsing for.", 2);
		
		int i = 1, j = 0;
		List<FCommand> fcmds = new ArrayList<FCommand>();

		List<String> inBrackets = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), inBrackets, i, "(", ")");
		inBrackets.add(";");
		
		List<String> winit = new ArrayList<String>();
		while (!inBrackets.get(j).equals(";")) {
			winit.add(inBrackets.get(j));
			j++;
		}
		winit.add(";");
		j++;
		this.setDecl(CommandBlockParser.parseSingleCommand(winit, this));
		
		List<String> wcond = new ArrayList<String>();
		while (!inBrackets.get(j).equals(";")) {
			wcond.add(inBrackets.get(j));
			j++;
		}
		j++;
		this.condition = new FExpression(this);
		this.condition.setWords(wcond);
		this.condition.parse();
		
		List<String> wincr = new ArrayList<String>();
		while (!inBrackets.get(j).equals(";")) {
			wincr.add(inBrackets.get(j));
			j++;
		}
		wincr.add(";");
		j++;
		this.increment = CommandBlockParser.parseSingleCommand(wincr, this);
		
		List<String> cmds = new ArrayList<String>();
		if (!getWords().get(i).equals(("{"))) {
			CommandBlockParser.getSingleCommand(getWords(), cmds, i);
			FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds, this);
			fcmds.add(singleCmd);
		} else {
			i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
			fcmds.addAll(CommandBlockParser.parseBlock(cmds, this));
		}
		setCommands(fcmds);
	}
	
	public void setDecl(FCommand decl) {
		this.decl = decl;
	}
	
	public FCommand getDecl() {
		return decl;
	}
	
	public FCommand getIncrement() {
		return increment;
	}
	
	public void setIncrement(FCommand increment) {
		this.increment = increment;
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

	@Override
	public Map<String, FVarType> getVariablesTable() {
		return variablesTable;
	}

	public ParentClass getParent() {
		return parent;
	}

	@Override
	public boolean addVarToTable(FVariable variable) {
		boolean result = Utils.addVarToTable(this.variablesTable, variable);
		Logger.log("For: Iterating through variables table:", 6);
		for ( Map.Entry<String, FVarType> entry : this.variablesTable.entrySet() ) {
			String key = entry.getKey();
			FVarType value = entry.getValue();
			Logger.log(value.getValue() + " " + key, 6);
		}
		return result;
	}
}

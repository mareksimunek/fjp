package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;

public class FIf extends FCommand {

	private FExpression condition;
	private List<FCommand> commands = new ArrayList<FCommand>();
	private List<FCommand> elseCommands = new ArrayList<FCommand>();
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing if.");
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
	
	public List<FCommand> getElseCommands() {
		return elseCommands;
	}
	
	public void setElseCommands(List<FCommand> elseCommands) {
		this.elseCommands = elseCommands;
	}
}

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;

public class FWhile extends FCommand {

	private FExpression condition;
	private List<FCommand> commands = new ArrayList<FCommand>();
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing while.");
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
}

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.parser.CommandBlockParser;
import cz.fav.fjp.project.parser.Processor;

public class FWhile extends FCommand {

	private FExpression condition;
	private List<FCommand> commands = new ArrayList<FCommand>();
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing while.");
		
		int i=1;
		FExpression fexpr = new FExpression();
		List<FCommand> fcmds  = new ArrayList<FCommand>();
		
		List<String> expr = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), expr, i, "(", ")");
		fexpr.setWords(expr);
		fexpr.parse();
		
		List<String> cmds = new ArrayList<String>();
		if (!getWords().get(i).equals(("{"))) {
			CommandBlockParser.getSingleCommand(getWords(), cmds, i);
			FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds);
			fcmds.add(singleCmd);
		}
		else {
			i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
			fcmds.addAll(CommandBlockParser.parseBlock(cmds));
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
}

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.parser.CommandBlockParser;
import cz.fav.fjp.project.parser.Processor;

public class FFor extends FCommand {

	private FExpression condition;
	private FCommand decl;
	private FCommand increment;
	private List<FCommand> commands = new ArrayList<FCommand>();
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing for.");
		
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
		this.setDecl(CommandBlockParser.parseSingleCommand(winit));
		
		List<String> wcond = new ArrayList<String>();
		while (!inBrackets.get(j).equals(";")) {
			wcond.add(inBrackets.get(j));
			j++;
		}
		j++;
		this.condition = new FExpression();
		this.condition.setWords(wcond);
		this.condition.parse();
		
		List<String> wincr = new ArrayList<String>();
		while (!inBrackets.get(j).equals(";")) {
			wincr.add(inBrackets.get(j));
			j++;
		}
		wincr.add(";");
		j++;
		this.increment = CommandBlockParser.parseSingleCommand(wincr);
		
		List<String> cmds = new ArrayList<String>();
		if (!getWords().get(i).equals(("{"))) {
			CommandBlockParser.getSingleCommand(getWords(), cmds, i);
			FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds);
			fcmds.add(singleCmd);
		} else {
			i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
			fcmds.addAll(CommandBlockParser.parseBlock(cmds));
		}
		
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
	
}

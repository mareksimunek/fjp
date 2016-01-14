package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.parser.CommandBlockParser;
import cz.fav.fjp.project.parser.Processor;

public class FIf extends FCommand implements ParentClass {

	private FExpression condition;
	private List<FCommand> commands = new ArrayList<FCommand>();
	private List<FCommand> elseCommands = new ArrayList<FCommand>();

	private ParentClass parent;

	public FIf(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing if.");
		
		int i = 1;
		FExpression fexpr = new FExpression(this);
		List<FCommand> fcmds = new ArrayList<FCommand>();
		List<FCommand> fcmdsElse = new ArrayList<FCommand>();

		List<String> expr = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), expr, i, "(", ")");
		fexpr.setWords(expr);
		fexpr.parse();

		List<String> cmds = new ArrayList<String>();
		if (!getWords().get(i).equals(("{"))) {
			CommandBlockParser.getSingleCommand(getWords(), cmds, i);
			FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds, this);
			fcmds.add(singleCmd);
		} else {
			i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
			fcmds.addAll(CommandBlockParser.parseBlock(cmds, this));
		}
		
		if (i < getWords().size()) {
			if (getWords().get(i).equals("else")) {
				i++;
				
				if (!getWords().get(i).equals(("{"))) {
					CommandBlockParser.getSingleCommand(getWords(), cmds, i);
					FCommand singleCmd = CommandBlockParser.parseSingleCommand(cmds, this);
					fcmdsElse.add(singleCmd);
				} else {
					i = Processor.getContentInsideBrackets(getWords(), cmds, i, "{", "}");
					fcmdsElse.addAll(CommandBlockParser.parseBlock(cmds, this));
				}
				
			}
		}

		this.setCondition(fexpr);
		this.setCommands(fcmds);
		this.setElseCommands(fcmdsElse);
		
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

	public ParentClass getParent() {
		return parent;
	}
}

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.parser.Processor;

public class FMethodCall extends FCommand implements ParentClass {

	private String methodName;
	private List<String> args;

	private ParentClass parent;

	public FMethodCall(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		Logger.log("Parsing mehod call: " + getWords().toString(), 2);

		List<String> name = new ArrayList<String>();
		int i = Processor.getContentInsideBrackets(getWords(), name, 0, getWords().get(0), "(");
		i--;
		this.setMethodName(getWords().get(0) + " " + name.toString());
		Logger.log(getMethodName(), 2);
		
		ArrayList<String> args = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), args, i, "(", ")");
		this.args = new ArrayList<String>();
		args.stream().filter(s -> !s.equals(",")).forEach(this.args::add);
		Logger.log(this.args.toString(), 7);
		
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setArgs(List<String> args) {
		this.args = args;
	}
	
	public List<String> getArgs() {
		return args;
	}

	public ParentClass getParent() {
		return parent;
	}
	

	
}

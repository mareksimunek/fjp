package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.parser.Processor;

public class FMethodCall extends FCommand {

	private String methodName;
	private List<String> args;
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing mehod call: " + getWords().toString());

		List<String> name = new ArrayList<String>();
		int i = Processor.getContentInsideBrackets(getWords(), name, 0, getWords().get(0), "(");
		i--;
		this.setMethodName(getWords().get(0) + " " + name.toString());
		System.out.println(getMethodName());
		
		ArrayList<String> args = new ArrayList<String>();
		i = Processor.getContentInsideBrackets(getWords(), args, i, "(", ")");
		this.args = new ArrayList<String>();
		args.stream().filter(s -> !s.equals(",")).forEach(this.args::add);
		System.out.println(this.args.toString());
		
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
	

	

	
}
package cz.fav.fjp.project.objects.commands;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FVariable;

public class FVarDeclaration extends FCommand {

	private FVariable var;
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing variable declaration: " + getWords().toString());
		this.var = new FVariable();
		this.var.setType(getWords().get(0));
		this.var.setName(getWords().get(1));
		
	}
	
	public void setVar(FVariable var) {
		this.var = var;
	}
	
	public FVariable getVar() {
		return var;
	}
	
}

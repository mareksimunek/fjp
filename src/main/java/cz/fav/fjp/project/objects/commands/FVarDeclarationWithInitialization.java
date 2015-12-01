package cz.fav.fjp.project.objects.commands;

import cz.fav.fjp.project.objects.ParsableObject;

public class FVarDeclarationWithInitialization extends ParsableObject {

	private String variable;
	private FExpression expression;
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing variable declararion with init: " + getWords().toString());
	}
	
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public void setExpression(FExpression expression) {
		this.expression = expression;
	}
	
	public FExpression getExpression() {
		return expression;
	}
	
}

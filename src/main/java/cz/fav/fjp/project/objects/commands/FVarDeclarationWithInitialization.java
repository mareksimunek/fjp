package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FVarType;
import cz.fav.fjp.project.objects.FVariable;

public class FVarDeclarationWithInitialization extends FCommand {

	private FVariable variable;
	private FExpression expression;
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing variable declararion with init: " + getWords().toString());
		
		List<String> toWhere = new ArrayList<String>();
		FVariable fvar = new FVariable();
		String operation = "";
		FExpression fexpr = new FExpression();
		List<String> what = new ArrayList<String>();
		int j=0;
		while (!getWords().get(j).contains("=")) toWhere.add(getWords().get(j++));
		operation = getWords().get(j++);
		while (!getWords().get(j).equals(";")) what.add(getWords().get(j++));
		
		fexpr.setWords(what);
		fexpr.parse();
		
		if (!operation.equals("=")) throw new Exception("Expected '=' in varriable declaration with inicilialization.");
		
		if (toWhere.size() == 2) {
			FVarType fVarType = new FVarType();
			fVarType.setValue(toWhere.get(0));
			fvar.setType(fVarType);
			fvar.setName(toWhere.get(1));
			this.setVariable(fvar);
			this.setExpression(fexpr);
		}
		else if (toWhere.size() == 2) { 
			System.err.println("Weird assingment: " + getWords().toString());
		}
		
	}
	
	public void setVariable(FVariable variable) {
		this.variable = variable;
	}
	
	public FVariable getVariable() {
		return variable;
	}
	
	public void setExpression(FExpression expression) {
		this.expression = expression;
	}
	
	public FExpression getExpression() {
		return expression;
	}
	
}

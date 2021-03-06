package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.*;

public class FAssignment extends FCommand implements ParentClass {

	private FVariable variable;
	private FExpression expr;
	private String operation;

	private ParentClass parent;

	public FAssignment(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		Logger.log("Parsing assignment: " + getWords().toString(), 2);
		
		List<String> toWhere = new ArrayList<String>();
		FVariable fvar = new FVariable(this);
		String operation = "";
		FExpression fexpr = new FExpression(this);
		List<String> what = new ArrayList<String>();
		int j=0;
		while (!getWords().get(j).contains("=")) toWhere.add(getWords().get(j++));
		operation = getWords().get(j++);
		while (j < getWords().size())
		{
			if (!getWords().get(j).equals(";"))
			{
				what.add(getWords().get(j));
			}
			j++;
		}
		
		fexpr.setWords(what);
		fexpr.parse();
		
		if (toWhere.size() == 1) {
			fvar.setName(toWhere.get(0));
			this.setExpr(fexpr);
			this.setVariable(fvar);
			this.setOperation(operation);
		}
		else if (toWhere.size() == 2) { 
			Logger.log("Weird assingment: " + getWords().toString(), 2);
		}
		
	}

	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public FVariable getVariable() {
		return variable;
	}

	public void setVariable(FVariable variable) {
		this.variable = variable;
	}

	public FExpression getExpr() {
		return expr;
	}

	public void setExpr(FExpression expr) {
		this.expr = expr;
	}

	public ParentClass getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return "FAssignment [variable=" + variable + ", expr=" + expr
				+ ", operation=" + operation + ", parent=" + parent + "]";
	}
	
	
}

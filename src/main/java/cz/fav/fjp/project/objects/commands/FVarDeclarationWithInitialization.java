package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.*;

public class FVarDeclarationWithInitialization extends FCommand implements ParentClass {

	private FAssignment assignment;

	private ParentClass parent;

	public FVarDeclarationWithInitialization(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing variable declararion with init: " + getWords().toString());
		
		List<String> toWhere = new ArrayList<String>();
		FVariable fvar = new FVariable(this);
		String operation;
		FExpression fexpr = new FExpression(this);
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
			fvar.setName(toWhere.get(1));
			fvar.setType(fVarType);

			FAssignment fAssignment = new FAssignment(this);
			fAssignment.setVariable(fvar);
			fAssignment.setOperation(operation);
			fAssignment.setExpr(fexpr);

			this.setAssignment(fAssignment);
		}
		else if (toWhere.size() == 2) { 
			System.err.println("Weird assingment: " + getWords().toString());
		}
		
	}

	public FAssignment getAssignment() {
		return assignment;
	}

	public void setAssignment(FAssignment assignment) {
		this.assignment = assignment;
	}

	public ParentClass getParent() {
		return parent;
	}
	
}

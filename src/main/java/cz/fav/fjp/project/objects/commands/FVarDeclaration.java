package cz.fav.fjp.project.objects.commands;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FVarType;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.objects.ParentClass;

public class FVarDeclaration extends FCommand implements ParentClass {

	private FVariable var;

	private ParentClass parent;

	public FVarDeclaration(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing variable declaration: " + getWords().toString());
		this.var = new FVariable(this);
		FVarType type = new FVarType();
		type.setValue(getWords().get(0));
		this.var.setName(getWords().get(1));
		this.var.setType(type);
	}
	
	public void setVar(FVariable var) {
		this.var = var;
	}
	
	public FVariable getVar() {
		return var;
	}

	public ParentClass getParent() {
		return parent;
	}

}

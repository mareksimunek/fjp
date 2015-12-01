package cz.fav.fjp.project.objects.commands;

import cz.fav.fjp.project.objects.ParsableObject;

public class FAssignment extends ParsableObject {

	private String varType;
	private String name;
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing expression: " + getWords().toString());
		
	}
	
	public String getVarType() {
		return varType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setVarType(String varType) {
		this.varType = varType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

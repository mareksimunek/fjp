package cz.fav.fjp.project.objects.commands;

import java.util.HashMap;
import java.util.Map;

import cz.fav.fjp.project.objects.ParsableObject;

public class FExpression extends ParsableObject {

	private String returnValueType; 
	
	
	
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing expression: " + getWords().toString());
		
	}
	
	public String getReturnValueType() {
		return returnValueType;
	}
	
	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}
	
}

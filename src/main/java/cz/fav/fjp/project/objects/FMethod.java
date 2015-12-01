package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.parser.FMethodParser;

public class FMethod extends ParsableObject {
	
	private String name;
	private List<String> arguments = new ArrayList<String>(); // TODO
	private List<String> modifiers;
	private String returnValueType;
	
	public FMethod() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void parse() throws Exception {
		FMethodParser.parse(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}

	public String getReturnValueType() {
		return returnValueType;
	}

	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}

}

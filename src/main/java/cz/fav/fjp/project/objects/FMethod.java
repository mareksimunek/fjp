package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.parser.FMethodParser;

public class FMethod extends ParsableObject {
	
	private String name;
	private List<FMethodArgument> arguments = new ArrayList<FMethodArgument>();
	private List<String> modifiers;
	private FVarType returnValueType;

	private List<FCommand> commnands;
	
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

	public List<FMethodArgument> getArguments() {
		return arguments;
	}

	public void setArguments(List<FMethodArgument> arguments) {
		this.arguments = arguments;
	}
	
	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}

	public FVarType getReturnValueType() {
		return returnValueType;
	}
	
	public void setReturnValueType(FVarType returnValueType) {
		this.returnValueType = returnValueType;
	}
	
	public List<FCommand> getCommnands() {
		return commnands;
	}
	
	public void setCommnands(List<FCommand> commnands) {
		this.commnands = commnands;
	}

}

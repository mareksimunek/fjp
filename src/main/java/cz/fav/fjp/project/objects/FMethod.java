package cz.fav.fjp.project.objects;

import java.util.*;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.parser.FMethodParser;

public class FMethod extends ParsableObject implements ParentClass, ObjectWithLocalVars {
	
	private String name;
	private List<FVariable> arguments = new ArrayList<FVariable>();
	private List<String> modifiers;
	private FVarType returnValueType;
	private Map<String, FVarType> variablesTable = new HashMap<>();

	private ParentClass parent;

	private List<FCommand> commnands;
	
	public FMethod(ParentClass parent) {
		this.parent = parent;
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

	public List<FVariable> getArguments() {
		return arguments;
	}

	public void setArguments(List<FVariable> arguments) {
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

	@Override
	public Map<String, FVarType> getVariablesTable() {
		return variablesTable;
	}

	public List<FCommand> getCommnands() {
		return commnands;
	}
	
	public void setCommnands(List<FCommand> commnands) {
		this.commnands = commnands;
	}

	@Override
	public boolean addVarToTable(FVariable variable)
	{
		boolean result = Utils.addVarToTable(this.variablesTable, variable);
		Logger.log("Method " + this.getName() + ": Iterating through variables table:", 6);
		for ( Map.Entry<String, FVarType> entry : this.variablesTable.entrySet() ) {
			String key = entry.getKey();
			FVarType value = entry.getValue();
			Logger.log(value.getValue() + " " + key, 6);
		}
		return result;
	}

	public ParentClass getParent() {
		return parent;
	}

}

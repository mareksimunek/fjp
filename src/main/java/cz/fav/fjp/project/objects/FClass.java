package cz.fav.fjp.project.objects;

import java.util.*;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.parser.FClassParser;

public class FClass extends ParsableObject implements ParentClass, ObjectWithLocalVars {

	private String name;
	private List<String> modifiers = new ArrayList<String>();
	private List<FMethod> methods = new ArrayList<FMethod>();
	private List<FAttribute> attributes = new ArrayList<FAttribute>();
	private Map<String, FVarType> variablesTable = new HashMap<>();
	
	public FClass() {
		super();
	}
	
	public List<String> getModifiers() {
		return modifiers;
	}
	
	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<FAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(List<FAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public List<FMethod> getMethods() {
		return methods;
	}
	
	public void setMethods(List<FMethod> methods) {
		this.methods = methods;
	}

	public Map<String, FVarType> getVariablesTable() {
		return variablesTable;
	}

	@Override
	public boolean addVarToTable(FVariable variable)
	{
		boolean result = Utils.addVarToTable(this.variablesTable, variable);
		System.out.println("Class " + this.getName() + ": Iterating through variables table:");
		for ( Map.Entry<String, FVarType> entry : this.variablesTable.entrySet() ) {
			String key = entry.getKey();
			FVarType value = entry.getValue();
			System.out.println(value.getValue() + " " + key);
		}
		System.out.println("");
		return result;
	}
	
	@Override
	public void parse() throws Exception {
		FClassParser.parse(this);
	}

	public ParentClass getParent() {
		return null;
	}
	
}

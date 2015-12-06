package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.parser.FClassParser;

public class FClass extends ParsableObject {

	private String name;
	private List<String> modifiers = new ArrayList<String>();
	private List<FMethod> methods = new ArrayList<FMethod>();
	private List<FAttribute> attributes = new ArrayList<FAttribute>();
	
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
	
	@Override
	public void parse() throws Exception {
		FClassParser.parse(this);
	}
	
}

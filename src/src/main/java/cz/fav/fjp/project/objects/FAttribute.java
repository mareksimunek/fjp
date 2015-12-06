package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

public class FAttribute {

	private List<String> modifiers = new ArrayList<String>();
	private String name;
	private String initialValue = null;
	private String type;
	
	public FAttribute() {
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

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

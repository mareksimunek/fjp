package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

public class FAttribute {

	private List<String> modifiers = new ArrayList<String>();
	private FVariable variable;
	private String initialValue = null;

	ParentClass parent;

	public FAttribute(ParentClass parent) {
		this.parent = parent;
	}

	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}

	public FVariable getVariable() {
		return variable;
	}

	public void setVariable(FVariable variable) {
		this.variable = variable;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	public ParentClass getParent() {
		return parent;
	}
	
}

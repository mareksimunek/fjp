package cz.fav.fjp.project.objects;

public class FVariable {
	
	private FVarType type;
	private String name;
	
	public FVariable() {
		super();
	}

	public FVarType getType() {
		return type;
	}
	
	public void setType(FVarType type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

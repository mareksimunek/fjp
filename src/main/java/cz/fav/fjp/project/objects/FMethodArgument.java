package cz.fav.fjp.project.objects;

public class FMethodArgument {

	private FVarType type;
	private String name;
	
	public FMethodArgument() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FVarType getType() {
		return type;
	}
	
	public void setType(FVarType type) {
		this.type = type;
	}
	
}

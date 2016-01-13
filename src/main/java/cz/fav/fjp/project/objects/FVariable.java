package cz.fav.fjp.project.objects;

public class FVariable implements ParentClass {
	
	private FVarType type;
	private String name;

	private ParentClass parent;

	public FVariable(ParentClass parent) {
		this.parent = parent;
	}

	public FVarType getType() {
		return type;
	}
	
	public void setType(FVarType type) {
		this.type = type;
		try {
			this.setVarToTable();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParentClass getParent() {
		return parent;
	}

	public void setVarToTable() throws Exception
	{
		ParentClass parentClass = this;
		while ((parentClass = parentClass.getParent()) != null)
		{
			if (parentClass instanceof ObjectWithLocalVars)
			{
				if (((ObjectWithLocalVars) parentClass).addVarToTable(this))
				{
					return;
				}
				else
				{
					throw new Exception("Local variable " + this.getName() + " already exists with different type!");
				}
			}
		}
		throw new Exception("No local variables table!");
	}
	
}

package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

public class FObjectInExp extends ParsableObject implements ParentClass{
	
	String name;
	List<String> params;
	private String methodName;
	private ParentClass parent;
	
	public FObjectInExp(ParentClass parent) {
		super();
		this.parent = parent;
		this.params = new LinkedList<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getParams() {
		return params;
	}
	public String getParamsString()
	{
		String params = "";
		for (int i = 0; i < getParams().size(); i++)
		{
			params += getParams().get(i) + ", ";
		}
		params = params.substring(0, params.length() - 2);
		return params;
	}
	public void addParam (String param){
		params.add(param);
	}
	public void setParams(List<String> params) {
		this.params = params;
	}


	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Override
	public String toString() {
		return "FObjectInExp [name=" + name + ", params=" + params
				+ ", methodName=" + methodName + "]";
	}

	@Override
	public void parse() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ParentClass getParent() {
		return parent;
	}


	

}

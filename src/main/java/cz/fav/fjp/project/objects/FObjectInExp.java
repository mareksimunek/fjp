package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

public class FObjectInExp {
	
	String name;
	List<String> params;
	private String methodName;

	
	public FObjectInExp(String name,
			List<String> params) {
		super();
		this.name = name;
		this.params = params;
	}
	
	public FObjectInExp() {
		super();
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


	

}

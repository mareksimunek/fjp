package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

public class FObjectInExp {
	
	int startIndex;
	int endIndex;
	String name;
	List<String> params;
	private String methodName;

	
	public FObjectInExp(int startIndex, int endIndex, String name,
			List<String> params) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.name = name;
		this.params = params;
	}
	
	public FObjectInExp(int startIndex) {
		super();
		this.startIndex = startIndex;
		this.params = new LinkedList<String>();
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
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
		return "FObjectInExp [startIndex=" + startIndex + ", endIndex="
				+ endIndex + ", name=" + name + ", params=" + params
				+ ", methodName=" + methodName + "]";
	}
	

}

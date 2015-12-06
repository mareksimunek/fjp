package cz.fav.fjp.project.objects;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.parser.FCompilationUnitParser;

public class FCompilationUnit extends ParsableObject {

	private String name;
	private List<FClass> classes = new ArrayList<FClass>();
	
	public FCompilationUnit() {
		super();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setClasses(List<FClass> classes) {
		this.classes = classes;
	}
	
	public List<FClass> getClasses() {
		return classes;
	}
	
	@Override
	public void parse() throws Exception {
		FCompilationUnitParser.parse(this);
	}
	
}

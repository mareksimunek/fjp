package cz.fav.fjp.project.objects;

import java.util.List;

public abstract class ParsableObject {

	private List<String> words;
	
	public List<String> getWords() {
		return words;
	}
	
	public void setWords(List<String> words) {
		this.words = words;
	}
	
	public abstract void parse() throws Exception;
	
}

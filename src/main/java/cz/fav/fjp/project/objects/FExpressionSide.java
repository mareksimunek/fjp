package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

import cz.fav.fjp.project.enums.Operators;

public class FExpressionSide extends ParsableObject {
	
	private String operator;

	@Override
	public void parse() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Parsing expressionSide: " + getWords().toString());
		
		
		int wordsSize= getWords().size();
		String word;
		FObjectInExp obj = null;
		boolean a;
		/*while( a = ((obj.getName() + "a").equals("gha") == true) == true){
			;
		}*/
		List<FObjectInExp> objectList = new LinkedList<FObjectInExp>();
		if(getWords().get(0).equalsIgnoreCase("(")){
			obj = new FObjectInExp();
		}
		for(int i=0; i < wordsSize; i++){
			word = getWords().get(i);
			if(Operators.ASSIGN_OPERATORS.contains(word)){
				
			}
			else if(word.equalsIgnoreCase("new")){
				obj = new FObjectInExp();
				setNewFObject(i, wordsSize, obj);
			}else if(word.equalsIgnoreCase(".")){
				obj = new FObjectInExp();
				setCalledFObject(i, wordsSize, obj);
				
			}
		}
		if(obj != null){
			System.out.println("Object from expression: " +obj.toString());
		}
		
		
	}
	

	void setNewFObject(int index, int size, FObjectInExp obj){
		String word;
		index++;
		if(index < 0)return;
		obj.setName(getWords().get(index));
		index++;
		int openBraces = 0;

		while(index< size){
			word = getWords().get(index);
			if(word.equalsIgnoreCase("(")){
				openBraces++;
			}else if(word.equalsIgnoreCase(")")){
				openBraces--;
				if(openBraces == 0){
					break;
				}
			}else if(word.equalsIgnoreCase(",")){
			}else{
				obj.addParam(word);
			}
			index++;

		}
	}
	void setCalledFObject(int index, int size, FObjectInExp obj){
		String word;
		index--;
		if(index < 0)return;
		obj.setName(getWords().get(index));
		index += 2;
		int openBraces = 0;
		if(index >= size)return;
		obj.setMethodName(getWords().get(index));

		while(index< size){
			word = getWords().get(index);
			if(word.equalsIgnoreCase("(")){
				openBraces++;
			}else if(word.equalsIgnoreCase(")")){
				openBraces--;
				if(openBraces == 0){
					break;
				}
			}else if(word.equalsIgnoreCase(",")){
			}else{
				obj.addParam(word);
			}
			index++;

		}
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}

package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cz.fav.fjp.project.parser.InfixToPostfix;

public class FExpression extends ParsableObject {

	private String returnValueType; 

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing expression: " + getWords().toString());

		String word;
		FObjectInExp obj = null;
		int tmpIndex;
		List<FObjectInExp> objectList = new LinkedList<FObjectInExp>();
		int wordsSize= getWords().size();
		for(int i=0; i < wordsSize; i++){
			word = getWords().get(i);
			if(word.equalsIgnoreCase("new")){
				obj = new FObjectInExp(i);
				setNewFObject(i, wordsSize, obj);
			}else if(word.equalsIgnoreCase(".")){
				tmpIndex = i;
				tmpIndex--;
				obj = new FObjectInExp(tmpIndex);
				setCalledFObject(tmpIndex, wordsSize, obj);
				
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
					obj.setEndIndex(index);
					break;
				}
			}else if(word.equalsIgnoreCase(",")){
			}else{
				obj.addParam(word);
			}
			index++;

		}
		if(obj.getEndIndex() ==0){
			obj.setEndIndex(index-1);
		}
	}
	void setCalledFObject(int index, int size, FObjectInExp obj){
		String word;
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
					obj.setEndIndex(index);
					break;
				}
			}else if(word.equalsIgnoreCase(",")){
			}else{
				obj.addParam(word);
			}
			index++;

		}
		if(obj.getEndIndex() ==0){
			obj.setEndIndex(index-1);
		}
	}


	public String getReturnValueType() {
		return returnValueType;
	}

	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}

}

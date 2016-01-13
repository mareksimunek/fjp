package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

import cz.fav.fjp.project.enums.Operators;


public class FExpression extends ParsableObject implements ParentClass {

	private String returnValueType; 
	private boolean isLeft;
	private ParentClass parent;

	List<FExpressionSide> fExpSideList;

	public FExpression(ParentClass parent) {
		this.parent = parent;
	}

	/**
	 * For every INFIX_OPS occurrence split getWords and creates new FExpressionSide
	 * right side saves the operator 
	 */
	@Override
	public void parse() throws Exception {
		System.out.println("Parsing expression: " + getWords().toString());
		int wordsSize= getWords().size();
		String word;
		int lastSplitIndex = 0;
		FExpressionSide fExpSide;
		fExpSideList = new LinkedList<FExpressionSide>();
		for(int i=0; i < wordsSize; i++){
			word = getWords().get(i);
			
			if(Operators.INFIX_OPS.contains(word)){
				fExpSide = new FExpressionSide(this);
				fExpSide.setWords(getWords().subList(lastSplitIndex, i));
				if(lastSplitIndex !=0){
					fExpSide.setOperator(word);
				}
				lastSplitIndex = i +1;
				fExpSideList.add(fExpSide);
			}
		}
		fExpSide = new FExpressionSide(this);
		fExpSide.setWords(getWords().subList(lastSplitIndex, wordsSize));
		fExpSideList.add(fExpSide);
		
		for(FExpressionSide f : fExpSideList){
			f.parse();
		}
		
		
	}




	public String getReturnValueType() {
		return returnValueType;
	}

	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}


	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public ParentClass getParent() {
		return parent;
	}
}

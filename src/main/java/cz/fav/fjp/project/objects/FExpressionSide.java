package cz.fav.fjp.project.objects;

import java.util.LinkedList;
import java.util.List;

import cz.fav.fjp.project.enums.Operators;
import cz.fav.fjp.project.objects.commands.FAssignment;

public class FExpressionSide extends ParsableObject implements ParentClass {

	private String operator;
	private ParentClass parent;
	private List<ParentClass> objectList = new LinkedList<>();


	public FExpressionSide(ParentClass parent) {
		super();
		this.parent = parent;
	}

	public List<ParentClass> getObjectList() {
		return objectList;
	}

	@Override
	public void parse() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Parsing expressionSide: " + " "+ getWords().toString()+ " Operator: "+ operator  );

		int wordsSize= getWords().size();
		String word;
		FObjectInExp obj = null;

		int openBraces = 0;
		List<String> saveWords = new LinkedList<String>();
		boolean typing = false;
		for(int i=0; i < wordsSize; i++){
			word = getWords().get(i);
			if(word.equalsIgnoreCase("(")){
				// if it is typing -> (char) 0, the brackets must remain
				if(openBraces > 0){
					saveWords.add(word);
				}
				if (getWords().size() > i + 2 && getWords().get(i + 2).equals(")") && (i <= 1 || !getWords().get(i - 2).equals(".")))
				{
					saveWords.add(word);
					typing = true;
				}
				openBraces++;
				
			}else if(word.equalsIgnoreCase(")")){
				openBraces--;
				if(openBraces > 0 || i > 1 && getWords().get(i - 2).equals("(")) {
					saveWords.add(word);		
				}

			}else if(openBraces > 0){
				saveWords.add(word);
			}else{
				
				if(!saveWords.isEmpty() && openBraces == 0){
					FExpressionSide f = new FExpressionSide(this);
					f.setWords(new LinkedList<String>(saveWords));
					f.parse();
					saveWords.clear();
					this.objectList.add(f);
					if (typing)
					{
						FExpressionSide expressionSide = new FExpressionSide(this);
						expressionSide.setWords(getWords().subList(i, getWords().size()));
						expressionSide.parse();
						this.objectList.add(expressionSide);
						typing = false;
					}
				}
				// TODO with declaration
				if(Operators.ASSIGN_OPERATORS.contains(word)){
					FAssignment assignment = new FAssignment(this);
					assignment.setWords(getWords());
					assignment.parse();

					this.objectList.add(assignment);
					break;
				}
				else if(word.equalsIgnoreCase("new")){
					obj = new FObjectInExp(this);
					setNewFObject(i, wordsSize, obj);
					this.objectList.add(obj);
				}else if(word.equalsIgnoreCase(".")){
					obj = new FObjectInExp(this);
					setCalledFObject(i, wordsSize, obj);
					this.objectList.add(obj);
				}

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
		int behindNameIndex = index;

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
			}else if(openBraces > 0){
				obj.addParam(word);
			}
			index++;

		}
		if(obj.getMethodName() == null){
			while(behindNameIndex < size){
				word = getWords().get(behindNameIndex);
				obj.addParam(word);
				behindNameIndex++;
			}
		}
	}
	void setCalledFObject(int index, int size, FObjectInExp obj){
		String word;
		index--;
		if(index < 0)return;
		obj.setName(getWords().get(index));

		if (obj.getName().equals(")"))
		{
			ParentClass parentClass = this.getObjectList().get(this.getObjectList().size() - 1);
			if (parentClass instanceof FExpressionSide)
			{
				if (((FExpressionSide)parentClass).getObjectList().size() > 0)
				{
					ParentClass assignement = ((FExpressionSide)parentClass).getObjectList().get(0);
					if (assignement instanceof FAssignment)
					{
						obj.setName(((FAssignment) assignement).getVariable().getName());
					}
				}
			}
		}

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
			}else if(openBraces > 0){
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


	@Override
	public ParentClass getParent() {
		
		return parent;
	}

}

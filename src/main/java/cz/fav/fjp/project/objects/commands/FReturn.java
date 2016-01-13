package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.ParentClass;

public class FReturn extends FCommand implements ParentClass {

	private FExpression returnValue;

	private ParentClass parent;

	public FReturn(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing return statement: " + getWords().toString());
		
		List<String> expr = new ArrayList<String>();
		expr.addAll(getWords());
		expr.remove(0);
		expr.remove(expr.size()-1);

		this.returnValue = new FExpression(this);
		this.returnValue.setWords(expr);
		this.returnValue.parse();
	}

	public void setReturnValue(FExpression returnValue) {
		this.returnValue = returnValue;
	}

	public FExpression getReturnValue() {
		return returnValue;
	}

	public ParentClass getParent() {
		return parent;
	}

}

package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;

public class FReturn extends FCommand {

	private FExpression returnValue;

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing return statement: " + getWords().toString());
		
		List<String> expr = new ArrayList<String>();
		expr.addAll(getWords());
		expr.remove(0);
		expr.remove(expr.size()-1);

		this.returnValue = new FExpression();
		this.returnValue.setWords(expr);
		this.returnValue.parse();
	}

	public void setReturnValue(FExpression returnValue) {
		this.returnValue = returnValue;
	}

	public FExpression getReturnValue() {
		return returnValue;
	}

}

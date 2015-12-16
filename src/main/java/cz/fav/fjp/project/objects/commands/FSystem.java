package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;

public class FSystem extends FCommand {

	private FExpression returnValue;

	@Override
	public void parse() throws Exception {
		System.out.println("Parsing System statement: " + getWords().toString());
		
		List<String> expr = new ArrayList<String>();
		expr.addAll(getWords());
        
        if (!expr.get(1).equals(".") || !expr.get(2).equals("out") || !expr.get(3).equals(".") || (!expr.get(4).equals("print") && !expr.get(4).equals("println")) || !expr.get(5).equals("("))
        {
            System.err.println("Weird System: " + expr.toString());
        }
        
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

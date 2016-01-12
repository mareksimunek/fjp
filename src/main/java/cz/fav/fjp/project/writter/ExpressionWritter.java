package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FExpression;

public class ExpressionWritter extends DefaultWritter<FExpression> {

	@Override
	public void transform(FExpression obj) {
		
		log("Writting expression: " + obj.getWords(), 1);
		
	}


	
	
}

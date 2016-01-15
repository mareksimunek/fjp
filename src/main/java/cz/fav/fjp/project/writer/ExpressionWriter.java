package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.objects.FExpression;

public class ExpressionWriter extends DefaultWriter<FExpression> {

	@Override
	public void transform(FExpression obj) {
		
		log("Writting expression: " + obj.getWords(), 1);

		obj.getfExpSideList().forEach( fExpressionSide -> {
			new ExpressionSideWriter().transform(fExpressionSide);
		});
		
	}


	
	
}

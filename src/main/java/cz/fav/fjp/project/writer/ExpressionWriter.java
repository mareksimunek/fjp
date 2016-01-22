package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FExpressionSide;

public class ExpressionWriter extends DefaultWriter<FExpression> {

	@Override
	public void transform(FExpression obj) throws Exception {
		
		Logger.log("Writing expression: " + obj.getWords(), 1);

		for (FExpressionSide fExpressionSide : obj.getfExpSideList()) {
			new ExpressionSideWriter().transform(fExpressionSide);
		}
		
	}


	
	
}

package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class ReturnWritter extends DefaultWritter<FReturn> {

	@Override
	public void transform(FReturn obj) {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		write("return ");
		new ExpressionWritter().transform(obj.getReturnValue());
	}
}

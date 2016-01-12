package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;
import cz.fav.fjp.project.writter.VarTypeWritter;

public class VarDeclWithInitWritter extends DefaultWritter<FVarDeclarationWithInitialization> {

	@Override
	public void transform(FVarDeclarationWithInitialization obj) {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		new VarTypeWritter().transform(obj.getVariable().getType());
		write(" " + obj.getVariable().getName() + " = ");
		new ExpressionWritter().transform(obj.getExpression());
	}
}

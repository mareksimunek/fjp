package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;
import cz.fav.fjp.project.writter.VarTypeWritter;

public class VarDeclWritter extends DefaultWritter<FVarDeclaration> {

	@Override
	public void transform(FVarDeclaration obj) {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		new VarTypeWritter().transform(obj.getVar().getType());
		write(" " + obj.getVar().getName());
	}
}

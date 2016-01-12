package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.CommandWritter;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class AssignmentWritter extends DefaultWritter<FAssignment> {

	@Override
	public void transform(FAssignment obj) {
		
		log("Writing assignment:", 3);
		
		write(obj.getVariable().getName() + " " + obj.getOperation() + " ");
		new ExpressionWritter().transform(obj.getExpr());
		
		
	}
}

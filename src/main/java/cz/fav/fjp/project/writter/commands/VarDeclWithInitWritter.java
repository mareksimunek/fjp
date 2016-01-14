package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;
import cz.fav.fjp.project.writter.VarTypeWritter;

public class VarDeclWithInitWritter extends DefaultWritter<FVarDeclarationWithInitialization> {

	@Override
	public void transform(FVarDeclarationWithInitialization obj) {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		new VarTypeWritter().transform(obj.getAssignment().getVariable().getType());
		String type = obj.getAssignment().getVariable().getType().getValue();
		if (type.equals("String"))
		{
			writeln(" " + obj.getAssignment().getVariable().getName() + "[STR_LEN];");
			new AssignmentWritter().transform(obj.getAssignment());
		}
		else if (type.equals("Scanner"))
		{

		}
		else
		{
			write(" ");
			new AssignmentWritter().transform(obj.getAssignment());
		}
	}
}

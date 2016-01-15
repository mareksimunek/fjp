package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.VarTypeWriter;

public class VarDeclWithInitWriter extends DefaultWriter<FVarDeclarationWithInitialization> {

	@Override
	public void transform(FVarDeclarationWithInitialization obj) throws Exception {
		
		Logger.log("Writing command: " + obj.getClass().getName(), 3);
		
		new VarTypeWriter().transform(obj.getAssignment().getVariable().getType());
		String type = obj.getAssignment().getVariable().getType().getValue();
		if (type.equals("String"))
		{
			writeln(" " + obj.getAssignment().getVariable().getName() + "[STR_LEN];");
			new AssignmentWriter().transform(obj.getAssignment());
		}
		else if (type.equals("Scanner"))
		{

		}
		else
		{
			write(" ");
			new AssignmentWriter().transform(obj.getAssignment());
		}
	}
}

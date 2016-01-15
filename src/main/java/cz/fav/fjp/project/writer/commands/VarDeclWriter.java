package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.VarTypeWriter;

public class VarDeclWriter extends DefaultWriter<FVarDeclaration> {

	@Override
	public void transform(FVarDeclaration obj) {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		new VarTypeWriter().transform(obj.getVar().getType());
		write(" " + obj.getVar().getName());
	}
}

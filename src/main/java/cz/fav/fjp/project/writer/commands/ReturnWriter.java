package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class ReturnWriter extends DefaultWriter<FReturn> {

	@Override
	public void transform(FReturn obj) throws Exception {
		
		log("Writing command: " + obj.getClass().getName(), 3);
		
		write("return ");
		new ExpressionWriter().transform(obj.getReturnValue());
	}
}

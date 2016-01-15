package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.commands.FWhile;
import cz.fav.fjp.project.writer.CommandWriter;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class WhileWriter extends DefaultWriter<FWhile> {

	@Override
	public void transform(FWhile obj) throws Exception {
		
		Logger.log("Writing while:", 3);
		
		write("while ( ");
		new ExpressionWriter().transform(obj.getCondition());
		writeln(" ) { ");
		
		for (FCommand c : obj.getCommands()) {
			CommandWriter commandWriter = new CommandWriter();
			commandWriter.transform(c);
		}
		
		writeln("}");
		
	}
}

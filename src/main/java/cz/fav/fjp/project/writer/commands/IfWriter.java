package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.writer.CommandWriter;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class IfWriter extends DefaultWriter<FIf> {

	@Override
	public void transform(FIf obj) throws Exception {
		
		Logger.log("Writing if:", 3);
		
		write("if ( ");
		new ExpressionWriter().transform(obj.getCondition());
		writeln(" ) { ");
		
		for (FCommand c : obj.getCommands()) {
			CommandWriter commandWriter = new CommandWriter();
			commandWriter.transform(c);
		}
		
		writeln("}");
		writeln();
		
		if (!obj.getElseCommands().isEmpty()) {
			writeln(" else { ");
			
			for (FCommand c : obj.getElseCommands()) {
				CommandWriter commandWriter = new CommandWriter();
				commandWriter.transform(c);
			}
			
			writeln("}");
			writeln();
			
		}
		
		
		
	}
}

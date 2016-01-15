package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.writer.CommandWriter;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class IfWriter extends DefaultWriter<FIf> {

	@Override
	public void transform(FIf obj) {
		
		log("Writing if:", 3);
		
		write("if ( ");
		new ExpressionWriter().transform(obj.getCondition());
		writeln(" ) { ");
		
		obj.getCommands().forEach( c -> {
			CommandWriter commandWriter = new CommandWriter();
			commandWriter.transform(c);
		});
		
		writeln("}");
		writeln();
		
		if (!obj.getElseCommands().isEmpty()) {
			writeln(" else { ");
			obj.getElseCommands().forEach( c -> {
				CommandWriter commandWriter = new CommandWriter();
				commandWriter.transform(c);
			});
			
			writeln("}");
			writeln();
			
		}
		
		
		
	}
}

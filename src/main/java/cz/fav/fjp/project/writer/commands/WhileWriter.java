package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.commands.FWhile;
import cz.fav.fjp.project.writer.CommandWriter;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class WhileWriter extends DefaultWriter<FWhile> {

	@Override
	public void transform(FWhile obj) {
		
		log("Writing while:", 3);
		
		write("while ( ");
		new ExpressionWriter().transform(obj.getCondition());
		writeln(" ) { ");
		
		obj.getCommands().forEach( c -> {
			CommandWriter commandWriter = new CommandWriter();
			commandWriter.transform(c);
		});
		
		writeln("}");
		
	}
}

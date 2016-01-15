package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.writer.CommandWriter;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class ForWriter extends DefaultWriter<FFor> {

	@Override
	public void transform(FFor obj) throws Exception {
		
		log("Writing for:", 3);
		
		write("for ( ");
		new CommandWriter().writeSemicolons(false).transform(obj.getDecl());
		new ExpressionWriter().transform(obj.getCondition());
		write(" ; ");
		new CommandWriter().writeSemicolons(false).transform(obj.getIncrement());
		writeln(" ) { ");
		
		for (FCommand c : obj.getCommands()) {
			CommandWriter commandWriter = new CommandWriter();
			commandWriter.transform(c);
		}
		
		writeln("}");
		
		
		
	}
}

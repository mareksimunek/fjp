package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FWhile;
import cz.fav.fjp.project.writter.CommandWritter;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class WhileWritter extends DefaultWritter<FWhile> {

	@Override
	public void transform(FWhile obj) {
		
		log("Writing while:", 3);
		
		write("while ( ");
		new ExpressionWritter().transform(obj.getCondition());
		writeln(" ) { ");
		
		obj.getCommands().forEach( c -> {
			CommandWritter commandWritter = new CommandWritter();
			commandWritter.transform(c);
		});
		
		writeln("}");
		
	}
}

package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.CommandWritter;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class ForWritter extends DefaultWritter<FFor> {

	@Override
	public void transform(FFor obj) {
		
		log("Writing for:", 3);
		
		write("for ( ");
		new CommandWritter().transform(obj.getDecl());
		write(" ; ");
		new ExpressionWritter().transform(obj.getCondition());
		write(" ; ");
		new CommandWritter().transform(obj.getIncrement());
		writeln(" ) { ");
		
		obj.getCommands().forEach( c -> {
			CommandWritter commandWritter = new CommandWritter();
			commandWritter.transform(c);
		});
		
		writeln("}");
		
		
		
	}
}

package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.CommandWritter;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class IfWritter extends DefaultWritter<FIf> {

	@Override
	public void transform(FIf obj) {
		
		log("Writing if:", 3);
		
		write("if ( ");
		new ExpressionWritter().transform(obj.getCondition());
		writeln(" ) { ");
		
		obj.getCommands().forEach( c -> {
			CommandWritter commandWritter = new CommandWritter();
			commandWritter.transform(c);
		});
		
		writeln("}");
		writeln();
		
	}
}

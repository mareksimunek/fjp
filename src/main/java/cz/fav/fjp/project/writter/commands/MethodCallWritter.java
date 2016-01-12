package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FMethodCall;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.DefaultWritter;

public class MethodCallWritter extends DefaultWritter<FMethodCall> {

	@Override
	public void transform(FMethodCall obj) {
		
		log("Writing method call: " + obj.getClass().getName(), 3);
		
		write(obj.getMethodName() + " ( ");
		for (int i = 0; i < obj.getArgs().size()-1; i++) {
			String arg = obj.getArgs().get(i);
			
			write(arg + ", ");
		}
		if (obj.getArgs().size() > 0) {
			write(obj.getArgs().get(obj.getArgs().size()-1));
		}
		
		write(")");
	}
}

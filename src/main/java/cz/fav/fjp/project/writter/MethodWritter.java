package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FMethod;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FMethodCall;
import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;

public class MethodWritter extends DefaultWritter<FMethod> {

	@Override
	public void transform(FMethod obj) {
		
		log("Writing method: " + obj.getName(), 1);
		
		log("Args:", 2);
		obj.getArguments().forEach( variable1 -> {
			log(variable1.getType().getValue() + " " + variable1.getName(), 2);
		});
		
		new VarTypeWritter().transform(obj.getReturnValueType());
		write(" " + obj.getName() + "(");

		for (int i = 0; i < obj.getArguments().size()-1; i++) {
			FVariable variable = obj.getArguments().get(i);
			new VarTypeWritter().transform(variable.getType());
			write(" " + variable.getName() + ",");
		}
		if (obj.getArguments().size() > 0) {
			FVariable variable = obj.getArguments().get(obj.getArguments().size()-1);
			new VarTypeWritter().transform(variable.getType());
			write(" " + variable.getName());
		}
		writeln(") {");
		
		obj.getCommnands().forEach( c -> {
			CommandWritter commandWritter = new CommandWritter();
//			write("\t");
			commandWritter.transform(c);
			if (c instanceof FVarDeclaration) write(";");
			if (c instanceof FVarDeclarationWithInitialization) write(";");
			if (c instanceof FReturn) write(";");
			if (c instanceof FMethodCall) write(";");
			if (c instanceof FAssignment) write(";");
			if (c instanceof FSystem) write(";");
			
			writeln();
		});
		
		writeln("}");
		writeln();
	}
}
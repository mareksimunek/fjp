package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FMethod;
import cz.fav.fjp.project.objects.FVariable;

public class MethodWriter extends DefaultWriter<FMethod> {

	@Override
	public void transform(FMethod obj) throws Exception {
		
		Logger.log("Writing method: " + obj.getName(), 1);
		Logger.log("Writing arg: " + obj.getArguments().toString(), 1);
		
		Logger.log("Args:", 2);
		obj.getArguments().forEach( variable1 -> {
			Logger.log(variable1.getType().getValue() + " " + variable1.getName(), 2);
		});
		
		new VarTypeWriter().transform(obj.getReturnValueType());
		write(" " + obj.getName() + "(");

		for (int i = 0; i < obj.getArguments().size()-1; i++) {
			FVariable variable = obj.getArguments().get(i);
			new VarTypeWriter().transform(variable.getType());
			if (variable.getType().getValue().equals("String")) write("*");
			write(" " + variable.getName() + ", ");
		}
		if (obj.getArguments().size() > 0) {
			FVariable variable = obj.getArguments().get(obj.getArguments().size()-1);
			new VarTypeWriter().transform(variable.getType());
			if (variable.getType().getValue().equals("String")) write("*");
			write(" " + variable.getName());
		}
		writeln(") {");
		
		for (FCommand c : obj.getCommnands()) {
			CommandWriter commandWriter = new CommandWriter();
//			write("\t");
			commandWriter.transform(c);
			writeln();
		}
		
		writeln("}");
		writeln();
	}

	public void writeHead(FMethod obj) {
		
		Logger.log("Writing head method: " + obj.getName(), 1);
		
		Logger.log("Args:", 2);
		obj.getArguments().forEach( variable1 -> {
			Logger.log(variable1.getType().getValue() + " " + variable1.getName(), 2);
		});
		
		new VarTypeWriter().transform(obj.getReturnValueType());
		write(" " + obj.getName() + "(");

		for (int i = 0; i < obj.getArguments().size()-1; i++) {
			FVariable variable = obj.getArguments().get(i);
			new VarTypeWriter().transform(variable.getType());
			if (variable.getType().getValue().equals("String")) write("*");
			write(" " + variable.getName() + ",");
		}
		if (obj.getArguments().size() > 0) {
			FVariable variable = obj.getArguments().get(obj.getArguments().size()-1);
			new VarTypeWriter().transform(variable.getType());
			if (variable.getType().getValue().equals("String")) write("*");
			write(" " + variable.getName());
		}
		writeln(");");
		writeln();
	}
}

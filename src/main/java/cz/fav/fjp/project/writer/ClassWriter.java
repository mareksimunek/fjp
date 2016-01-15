package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FClass;
import cz.fav.fjp.project.objects.FMethod;

public class ClassWriter extends DefaultWriter<FClass> {

	@Override
	public void transform(FClass obj) throws Exception {
		
		Logger.log("Writting class: " + obj.getName(), 1);
		
		obj.getAttributes().forEach( a -> {
			if (a.getVariable().getType().getValue().equals("Scanner")) return;
			
			a.getModifiers().forEach( s -> {
				if (s.equals("final")) write("const ");
				if (s.equals("static")) write("static ");
			});
			
			new VarTypeWriter().transform(a.getVariable().getType());
			if (a.getVariable().getType().getValue().equals("String")) write("*");
			write(" ");
			write(a.getVariable().getName() + " ");
			if (a.getInitialValue() != null) write(" = " + a.getInitialValue());
			writeln(";");
		});
		writeln();

		obj.getMethods().forEach( m -> {
			MethodWriter methodWriter = new MethodWriter();
			methodWriter.writeHead(m);
		});

		for (FMethod m : obj.getMethods()) {
			MethodWriter methodWriter = new MethodWriter();
			methodWriter.transform(m);
		}
		
		writeln();
		
		
	}


	
	
}

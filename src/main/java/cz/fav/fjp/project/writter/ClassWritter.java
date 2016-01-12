package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FClass;

public class ClassWritter extends DefaultWritter<FClass> {

	@Override
	public void transform(FClass obj) {
		
		log("Writting class: " + obj.getName(), 1);
		
		obj.getAttributes().forEach( a -> {
			a.getModifiers().forEach( s -> {
				if (s.equals("final")) write("const ");
				if (s.equals("static")) write("static ");
			});
			
			new VarTypeWritter().transform(a.getType());
			write(" ");
			write(a.getName() + " ");
			if (a.getInitialValue() != null) write(" = " + a.getInitialValue());
			writeln(";");
		});
		writeln();
		
		obj.getMethods().forEach( m -> {
			MethodWritter methodWritter = new MethodWritter();
			methodWritter.transform(m);
		});
		writeln();
		
		
	}


	
	
}

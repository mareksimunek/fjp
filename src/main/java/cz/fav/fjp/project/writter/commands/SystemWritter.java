package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.writter.DefaultWritter;

public class SystemWritter extends DefaultWritter<FSystem> {

	@Override
	public void transform(FSystem obj) {
		
		log("Writing system call:", 3);
		
//		write("System call: " + obj.getWords().toString());
		write(obj.getReturnValue().getReturnValueType());
	}
}

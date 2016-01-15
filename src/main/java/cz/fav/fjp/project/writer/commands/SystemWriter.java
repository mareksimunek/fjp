package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.writer.DefaultWriter;

public class SystemWriter extends DefaultWriter<FSystem> {

	@Override
	public void transform(FSystem obj) {
		
		log("Writing system call:", 3);
		
		write(obj.parseBrackets(obj.getWords()));
	}
}

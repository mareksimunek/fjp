package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.objects.commands.FMethodCall;
import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.objects.commands.FWhile;
import cz.fav.fjp.project.writer.commands.AssignmentWriter;
import cz.fav.fjp.project.writer.commands.ForWriter;
import cz.fav.fjp.project.writer.commands.IfWriter;
import cz.fav.fjp.project.writer.commands.MethodCallWriter;
import cz.fav.fjp.project.writer.commands.ReturnWriter;
import cz.fav.fjp.project.writer.commands.SystemWriter;
import cz.fav.fjp.project.writer.commands.VarDeclWithInitWriter;
import cz.fav.fjp.project.writer.commands.VarDeclWriter;
import cz.fav.fjp.project.writer.commands.WhileWriter;

public class CommandWriter extends DefaultWriter<FCommand> {

	private boolean writeSemicolons = true;
	
	@Override
	public void transform(FCommand obj) throws Exception {
		
		Logger.log("Writing command: " + obj.getClass().getName(), 5);
		
		if (obj instanceof FFor) new ForWriter().transform((FFor) obj);
		else if (obj instanceof FIf) new IfWriter().transform((FIf) obj);
		else if (obj instanceof FMethodCall) new MethodCallWriter().transform((FMethodCall) obj);
		else if (obj instanceof FReturn) new ReturnWriter().transform((FReturn) obj);
		else if (obj instanceof FVarDeclarationWithInitialization) new VarDeclWithInitWriter().transform((FVarDeclarationWithInitialization) obj);
		else if (obj instanceof FVarDeclaration) new VarDeclWriter().transform((FVarDeclaration) obj);
		else if (obj instanceof FWhile) new WhileWriter().transform((FWhile) obj);
		else if (obj instanceof FSystem) new SystemWriter().transform((FSystem) obj);
		else if (obj instanceof FAssignment) new AssignmentWriter().transform((FAssignment) obj);
		else writeln("**** unhandled command: " + obj.getClass().getName());
		
		if (!writeSemicolons) return;
		
		if (obj instanceof FVarDeclaration) write(";");
		if (obj instanceof FVarDeclarationWithInitialization) write(";");
		if (obj instanceof FReturn) write(";");
		if (obj instanceof FMethodCall) write(";");
		if (obj instanceof FAssignment) write(";");
		if (obj instanceof FSystem) write(";");

	}
	
	public CommandWriter writeSemicolons(boolean writeSemicolons) {
		this.writeSemicolons = writeSemicolons;
		return this;
	}
}

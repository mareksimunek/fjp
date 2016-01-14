package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FIf;
import cz.fav.fjp.project.objects.commands.FMethodCall;
import cz.fav.fjp.project.objects.commands.FReturn;
import cz.fav.fjp.project.objects.commands.FSystem;
import cz.fav.fjp.project.objects.commands.FVarDeclaration;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.objects.commands.FWhile;
import cz.fav.fjp.project.writter.commands.AssignmentWritter;
import cz.fav.fjp.project.writter.commands.ForWritter;
import cz.fav.fjp.project.writter.commands.IfWritter;
import cz.fav.fjp.project.writter.commands.MethodCallWritter;
import cz.fav.fjp.project.writter.commands.ReturnWritter;
import cz.fav.fjp.project.writter.commands.SystemWritter;
import cz.fav.fjp.project.writter.commands.VarDeclWithInitWritter;
import cz.fav.fjp.project.writter.commands.VarDeclWritter;
import cz.fav.fjp.project.writter.commands.WhileWritter;

public class CommandWritter extends DefaultWritter<FCommand> {

	private boolean writeSemicolons = true;
	
	@Override
	public void transform(FCommand obj) {
		
		log("Writing command: " + obj.getClass().getName(), 5);
		
		if (obj instanceof FFor) new ForWritter().transform((FFor) obj);
		else if (obj instanceof FIf) new IfWritter().transform((FIf) obj);
		else if (obj instanceof FMethodCall) new MethodCallWritter().transform((FMethodCall) obj);
		else if (obj instanceof FReturn) new ReturnWritter().transform((FReturn) obj);
		else if (obj instanceof FVarDeclarationWithInitialization) new VarDeclWithInitWritter().transform((FVarDeclarationWithInitialization) obj);
		else if (obj instanceof FVarDeclaration) new VarDeclWritter().transform((FVarDeclaration) obj);
		else if (obj instanceof FWhile) new WhileWritter().transform((FWhile) obj);
		else if (obj instanceof FSystem) new SystemWritter().transform((FSystem) obj);
		else if (obj instanceof FAssignment) new AssignmentWritter().transform((FAssignment) obj);
		else writeln("**** unhandled command: " + obj.getClass().getName());
		
		if (!writeSemicolons) return;
		
		if (obj instanceof FVarDeclaration) write(";");
		if (obj instanceof FVarDeclarationWithInitialization) write(";");
		if (obj instanceof FReturn) write(";");
		if (obj instanceof FMethodCall) write(";");
		if (obj instanceof FAssignment) write(";");
		if (obj instanceof FSystem) write(";");

	}
	
	public CommandWritter writeSemicolons(boolean writeSemicolons) {
		this.writeSemicolons = writeSemicolons;
		return this;
	}
}

package cz.fav.fjp.project.writter.commands;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FObjectInExp;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.objects.commands.FFor;
import cz.fav.fjp.project.objects.commands.FVarDeclarationWithInitialization;
import cz.fav.fjp.project.writter.CommandWritter;
import cz.fav.fjp.project.writter.DefaultWritter;
import cz.fav.fjp.project.writter.ExpressionWritter;

public class AssignmentWritter extends DefaultWritter<FAssignment> {

	@Override
	public void transform(FAssignment obj) {
		
		log("Writing assignment:", 3);

		boolean specialType = false;
		if (obj.getOperation().equals("="))
		{
			String type = "";
			try {
				type = Utils.getVarType(obj.getVariable().getName(), obj);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (type.equals("String"))
			{
				specialType = true;
				write("strcpy(" + obj.getVariable().getName() + ", ");
				new ExpressionWritter().transform(obj.getExpr());
				write(")");
			}
			if (type.equals("Scanner"))
			{
				// if it is Scanner, it must be System.in, nothing else is currently supported
				ParentClass p = obj.getExpr().getfExpSideList().get(0).getObjectList().get(0);
				if (p instanceof FObjectInExp)
				{
					if (((FObjectInExp) p).getParams().size() >= 2 && ((FObjectInExp) p).getParams().get(0).equals("System") && ((FObjectInExp) p).getParams().get(1).equals(".") && ((FObjectInExp) p).getParams().get(2).equals("in"))
					{
						specialType = true;
					}
				}
			}
		}

		if (!specialType)
		{
			write(obj.getVariable().getName() + " " + obj.getOperation() + " ");
			new ExpressionWritter().transform(obj.getExpr());
			writeln(";");
		}
	}
}

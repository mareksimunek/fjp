package cz.fav.fjp.project.writer.commands;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.FExpressionSide;
import cz.fav.fjp.project.objects.FObjectInExp;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.writer.DefaultWriter;
import cz.fav.fjp.project.writer.ExpressionWriter;

public class AssignmentWriter extends DefaultWriter<FAssignment> {

	@Override
	public void transform(FAssignment obj) throws Exception {

		log("Writing assignment: " + obj.toString(), 3);

		boolean specialType = false;
		boolean isInitilization = isInitilization(obj);

		if (obj.getOperation().equals("=") && !isInitilization)
		{
			
			String type = "";
			type = Utils.getVarType(obj.getVariable().getName(), obj);

			if (type.equals("String"))
			{
				specialType = true;
				write("strcpy(" + obj.getVariable().getName() + ", ");
				new ExpressionWriter().transform(obj.getExpr());
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
			if(!isInitilization){
				write(obj.getVariable().getName() + " " + obj.getOperation() + " ");
			}
			new ExpressionWriter().transform(obj.getExpr());
			writeln(";");
			
		
		}

	}


	boolean isInitilization (FAssignment obj){
		FExpression f = obj.getExpr();
		for(FExpressionSide fExpSide : f.getfExpSideList()){
			for( ParentClass child : fExpSide.getObjectList()){
				if(child instanceof FObjectInExp && ((FObjectInExp) child).isNewInitilization()){
					return true;
				}
			}
		}
		return false;
	}
}

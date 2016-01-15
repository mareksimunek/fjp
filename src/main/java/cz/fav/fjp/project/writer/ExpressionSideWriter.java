package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.objects.FExpressionSide;
import cz.fav.fjp.project.objects.FObjectInExp;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.writer.commands.AssignmentWriter;

public class ExpressionSideWriter extends DefaultWriter<FExpressionSide> {

	@Override
	public void transform(FExpressionSide obj) throws Exception {

		log("Writting expression side: " + obj.getWords(), 1);

		if(obj.getObjectList().isEmpty()){

			obj.getWords().forEach(  w->{
				write(w +" ");
			});
			if(obj.getOperator() != null){
				write(" " + obj.getOperator()  +" ");
			}

		}else{
			for (ParentClass object : obj.getObjectList()) {
				if (object instanceof FAssignment)
				{
					new AssignmentWriter().transform((FAssignment)object);
				}
				else if (object instanceof FExpressionSide)
				{
					new ExpressionSideWriter().transform((FExpressionSide)object);
				}
				else if (object instanceof FObjectInExp)
				{
					new ObjectInExpWriter().transform((FObjectInExp)object);
				}
				else
				{
					writeln("Unknown object in expression side!");
				}
			}
			if (obj.getOperator() != null)
			{
				write(obj.getOperator());
			}
		}

	}




}

package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FExpressionSide;
import cz.fav.fjp.project.objects.FObjectInExp;
import cz.fav.fjp.project.objects.commands.FAssignment;
import cz.fav.fjp.project.writter.commands.AssignmentWritter;

public class ExpressionSideWritter extends DefaultWritter<FExpressionSide> {

	@Override
	public void transform(FExpressionSide obj) {
		
		log("Writting expression side: " + obj.getWords(), 1);

		obj.getObjectList().forEach(object -> {
			if (object instanceof FAssignment)
			{
				new AssignmentWritter().transform((FAssignment)object);
			}
			else if (object instanceof FExpressionSide)
			{
				new ExpressionSideWritter().transform((FExpressionSide)object);
			}
			else if (object instanceof FObjectInExp)
			{
				new ObjectInExpWritter().transform((FObjectInExp)object);
			}
			else
			{
				writeln("Unknown object in expression side!");
			}
		});
		
	}


	
	
}

package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FObjectInExp;
import cz.fav.fjp.project.objects.ObjectWithLocalVars;
import cz.fav.fjp.project.objects.ParentClass;
import cz.fav.fjp.project.objects.commands.FAssignment;

public class ObjectInExpWritter extends DefaultWritter<FObjectInExp> {

	@Override
	public void transform(FObjectInExp obj) {

		log("Writting object in expression: " + obj.getName(), 1);

		if(obj.isNewInitilization()){
			objectInitialization(obj);
		}else{
			objectCall(obj);
		}

	}
	public void objectInitialization(FObjectInExp obj){
		String typeName = obj.getName();
		boolean canWrite = true;
		switch (typeName)
		{
		case "String":
			write("char");
			break;
		case "int":
		case "Integer":
			write("int");
			break;
		case "Scanner":
			canWrite = false;
			break;
		default:
			write("Unknown init class " + typeName );
			canWrite = false;
		}
		if(canWrite){
			ParentClass p = obj;
			while( !(p instanceof FAssignment)){
				p = p.getParent();
			}
			write(" " +((FAssignment) p).getVariable().getName());
			obj.getParams().forEach( par ->{
				write( par);
			});
		}

	}


	public void objectCall(FObjectInExp obj){
		String typeName = "";
		try {
			typeName = Utils.getVarType(obj.getName(), obj);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		switch (typeName)
		{
		case "Scanner":
			write("readLine()");
			break;
		case "String":
			String method = obj.getMethodName();
			if (method.equals("charAt"))
			{
				if(obj.isUsedByMethodBefore()){
					write( "[" + obj.getParamsString() + "] ");
				}else{
					write(obj.getName() + "[" + obj.getParamsString() + "] ");
				}

			}
			else if (method.equals("length"))
			{
				write("strlen(" + obj.getName() + ")");
			}
			else
			{
				log("Unsupported method " + method + "!!!", 1);
			}
			break;
		default:
			write("Unknown class " + typeName + "!!!");
		}
	}

}

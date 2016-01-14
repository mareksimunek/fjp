package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FObjectInExp;

public class ObjectInExpWritter extends DefaultWritter<FObjectInExp> {

    @Override
    public void transform(FObjectInExp obj) {

        log("Writting object in expression: " + obj.getName(), 1);

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
                	if(method.equals("(")){
                		 write( "[" + obj.getParamsString() + "]");
                	}else{
                		 write(obj.getName() + "[" + obj.getParamsString() + "]");
                	}
                   
                }
                break;
            default:
                write("Unknown class " + typeName + "!!!");
        }

    }

}

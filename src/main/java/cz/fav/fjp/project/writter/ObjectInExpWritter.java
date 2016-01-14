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
            default:
                write("Unknown class " + typeName + "!!!");
        }

    }

}

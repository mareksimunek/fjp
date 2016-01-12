package cz.fav.fjp.project.writter;

import cz.fav.fjp.project.objects.FVarType;

public class VarTypeWritter extends DefaultWritter<FVarType> {

	@Override
	public void transform(FVarType obj) {
		if (obj.getValue().equals("String")) write("char*");
		else if (obj.getValue().equals("String[]")) write("char**");
		else write(obj.getValue());
	}


	
	
}

package cz.fav.fjp.project.writer;

import cz.fav.fjp.project.objects.FVarType;

public class VarTypeWriter extends DefaultWriter<FVarType> {

	@Override
	public void transform(FVarType obj) {
		if (obj.getValue().equals("String")) write("char");
		else if (obj.getValue().equals("String[]")) write("char**");
		else write(obj.getValue());
	}


	
	
}

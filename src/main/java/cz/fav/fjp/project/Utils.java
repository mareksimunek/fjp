package cz.fav.fjp.project;

import cz.fav.fjp.project.objects.FVarType;
import cz.fav.fjp.project.objects.FVariable;

import java.util.List;
import java.util.Map;

public class Utils {

	public static void prettyPrint(List<String> words) {
		words.forEach( s -> {
			System.out.print(s + " ");
			if (s.equals(";")) System.out.println();
			if (s.equals("{")) System.out.println();
			if (s.equals("}")) System.out.println();
		});
	}

	public static boolean addVarToTable(Map<String, FVarType> variablesTable, FVariable variable)
	{
		FVarType type = variablesTable.get(variable.getName());
		// if the variable is not yet in the table we insert it normally
		if (type == null)
		{
			variablesTable.put(variable.getName(), variable.getType());
			return true;
		}

		// if it is there with the same type it's ok as well
		if (type.equals(variable.getType()))
		{
			return true;
		}
		// otherwise an error will be thrown
		return false;
	}
	
}

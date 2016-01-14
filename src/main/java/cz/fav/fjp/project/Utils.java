package cz.fav.fjp.project;

import cz.fav.fjp.project.objects.FClass;
import cz.fav.fjp.project.objects.FMethod;
import cz.fav.fjp.project.objects.FVarType;
import cz.fav.fjp.project.objects.FVariable;
import cz.fav.fjp.project.objects.ObjectWithLocalVars;
import cz.fav.fjp.project.objects.ParentClass;

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

	public static String getVarType(String name, ParentClass parentClass) throws Exception
	{
		while ((parentClass = parentClass.getParent()) != null)
		{
			if (parentClass instanceof ObjectWithLocalVars)
			{
				Map<String, FVarType> variablesTable = ((ObjectWithLocalVars) parentClass).getVariablesTable();
				FVarType varType = variablesTable.get(name);
				if (varType != null)
				{
					return varType.getValue();
				}
			}
		}
		throw new Exception("No such variable exists!");
	}
	
	public static String getMethodReturnType(String name, ParentClass parentClass) throws Exception
	{
		while ((parentClass = parentClass.getParent()) != null)
		{
			if (parentClass instanceof FClass)
			{
				List<FMethod> methods = ((FClass) parentClass).getMethods();
				for (FMethod method : methods) {
					if (method.getName().equals(name)) {
						if (method.getReturnValueType() != null) {
							return method.getReturnValueType().getValue();
						}
					}
				}
			}
		}
		return null;
	}
}

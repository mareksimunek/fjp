package cz.fav.fjp.project.objects;

import java.util.Map;

public interface ObjectWithLocalVars {

    public Map<String, FVarType> getVariablesTable();

    public boolean addVarToTable(FVariable variable);

}

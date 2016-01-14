package cz.fav.fjp.project.objects.commands;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.Utils;
import cz.fav.fjp.project.objects.FCommand;
import cz.fav.fjp.project.objects.FExpression;
import cz.fav.fjp.project.objects.ParentClass;

public class FSystem extends FCommand implements ParentClass {

	private static final String PRINTLN = "println";
    private static final String PRINTF = "printf";
    
    private static final String INT = "int";
    private static final String STRING = "String";
    private static final String FLOAT = "float";
    private static final String CHAR = "char";
    private static final String UNKNOWN = "Unknown";
	
	private FExpression returnValue;

	private ParentClass parent;

	public FSystem(ParentClass parent) {
		this.parent = parent;
	}

	@Override
	public void parse() throws Exception {
System.out.println("Parsing System statement: " + getWords().toString());
		
		List<String> expr = new ArrayList<String>();
		expr.addAll(getWords());
		
		String partText = PRINTF + "(\"";
        String partParams = "";
		
        if (!expr.get(1).equals(".") || !expr.get(2).equals("out") || !expr.get(3).equals(".") || (!expr.get(4).equals("print") && !expr.get(4).equals("println")) || !expr.get(5).equals("("))
        {
            System.err.println("Weird System: " + expr.toString());
            
        }  else {

        	int i = 6;
        	boolean isPreviousInt = false;
        	while (i < expr.size() - 2) {
        		String currentExpr = expr.get(i);
        		
        		if (currentExpr.charAt(0) == '\"' && currentExpr.charAt(currentExpr.length() - 1) == '\"') {
                	partText += currentExpr.replaceAll("\"", " ").trim() + " ";
                } else if (currentExpr.equals("#STRING_3")) {
                	partText += currentExpr.replaceAll("\"", " ").trim() + " ";
                } else if (!currentExpr.equals("+")) {
                	
                	String dataType;
                	if (isInteger(currentExpr)) {
                		dataType = getTypeOfExpression(currentExpr);
                		if (isPreviousInt) {
                			partParams += " + " + expr.get(i);
                		} else {
                			partParams += ", " + expr.get(i);
                			partText += getPrintfParamByExprType(dataType);
                		}
                		isPreviousInt = true;
                	} else {
                		String tempExp = "";
                		while (true) {
                			currentExpr = expr.get(i);
                			tempExp += currentExpr;
                			if (expr.get(i + 1).equals("(")) {
                				i += 2;
                				tempExp += "(";
                				while (!expr.get(i).equals(")")) {
                					tempExp += expr.get(i);
                					i++; 
                				}
                				tempExp += ")";
                			}
                			if (!expr.get(i + 1).equals(".")) {
                				break;
                			} else {
                				tempExp += ".";
                			}
                			i += 2;
                		}
                		dataType = getTypeOfExpression(currentExpr);
                		if (dataType.equals(INT)) {
                			if (isPreviousInt) {
                				partParams += " + " + tempExp;
                			} else {
                				partParams += ", " + tempExp;
                				partText += getPrintfParamByExprType(dataType);
                				isPreviousInt = true;
                			}
                		} else {
                			partParams += ", " + tempExp;
                			partText += getPrintfParamByExprType(dataType);
                			isPreviousInt = false;
                		}
                	}
                	
                }
        		i++;
        	}
 
            if (expr.get(4).equals(PRINTLN)) {
                partText += "\\n\"";
            } else {
                partText += "\"";
            }
           
            partText += partParams.trim() + ")";
            System.out.println("Translate sysout:" + partText);
            
        }
        
        FExpression expression = new FExpression(this);
        expression.setReturnValueType(partText);
		
        this.returnValue = expression;
		this.returnValue.setWords(expr);
		this.returnValue.parse();
	}

	public void setReturnValue(FExpression returnValue) {
		this.returnValue = returnValue;
	}

	public FExpression getReturnValue() {
		return returnValue;
	}

	public ParentClass getParent() {
		return parent;
	}

	private boolean isInteger(String expression) {
        return expression.matches("[0-9]+");
    }
	
	private String getTypeOfExpression(String expr) {
		System.out.println("Expression in process: " + expr);
		try {
			if (isInteger(expr)) {
				return INT;
			} else {
				String type = Utils.getMethodReturnType(expr, this);
				if (type != null) {
					System.out.println("getMethodReturnType :" + type);
					return type;
				}
				return Utils.getVarType(expr, this);
			}
		} catch (Exception e) {
			return UNKNOWN;
		}
	}
	
	private String getPrintfParamByExprType(String exprType) {
		try {
			if (exprType.equals(INT)) {
				return "%d ";
			} else if (exprType.equals(STRING)) {
				return "%s ";
			} else if (exprType.equals(FLOAT)) {
				return "%f ";
			} else if (exprType.equals(CHAR)) {
				return "%c ";
			} else {
				return "%-1 ";
			}
		} catch (Exception e) {
			return "%-1 ";
		}
	}
}

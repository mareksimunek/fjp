package cz.fav.fjp.project.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyWords {

	public static final List<String> modifiers = new ArrayList<String>(
			Arrays.asList("public,protected,private,static,abstract,final,native,synchronized,transient,volatile,strictfp".split(",")));
	
	public static final String kwClass = "class";
	
	public static final String kwIf = "if";
	public static final String kwWhile = "while";
	public static final String kwFor = "for";
	public static final String kwReturn = "return";
    public static final String kwSystem = "System";
}

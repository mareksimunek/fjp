package cz.fav.fjp.project.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operators {

	public static final List<String> INFIX_OPS = new ArrayList<String>( 
			Arrays.asList("||,&&,|,^,&,==,!=,<,>,<=,>=".split(",")));
			
	

	public static final List<String> ASSIGN_OPERATORS = new ArrayList<String>( 
		Arrays.asList("=,+=,-=,*=,/=,%=,&=,^=,|=,<<=,>>=,>>>=".split(",")));
		
	
}

	
	


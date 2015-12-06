package cz.fav.fjp.project;

import java.util.List;

public class Utils {

	public static void prettyPrint(List<String> words) {
		words.forEach( s -> {
			System.out.print(s + " ");
			if (s.equals(";")) System.out.println();
			if (s.equals("{")) System.out.println();
			if (s.equals("}")) System.out.println();
		});
	}
	
}

package cz.fav.fjp.project.parser;

import java.util.ArrayList;
import java.util.List;

import cz.fav.fjp.project.enums.KeyWords;

public class Processor {

	public static int getContentInsideBrackets(
			List<String> input,
			List<String> output,
			int position,
			String openingBracket,
			String closingBracket) throws Exception {

		int deep = 1;
		if (!input.get(position).equals(openingBracket)) {
			throw new Exception("Expected opening bracket as the first word on given position.");
		}
		int i = position + 1;
		ArrayList<String> ret = new ArrayList<String>();

		while (true) {
			if (input.get(i).equals(openingBracket))
				deep++;
			if (input.get(i).equals(closingBracket))
				deep--;

			if (deep == 0) {
				i++;
				break;
			} else {
				ret.add(input.get(i));
			}

			i++;
		}

		output.clear();
		output.addAll(ret);

		return i;
	}

	public static int getModifiers(List<String> input, List<String> output, int position) {
		output.clear();
		int i = position;
		nextWord: while (true) {
			String word = input.get(i);

			for (String modifier : KeyWords.modifiers) {
				if (word.equals(modifier)) {
					output.add(modifier);
					i++;
					continue nextWord;
				}
			}
			return i;
		}
	}
	
}

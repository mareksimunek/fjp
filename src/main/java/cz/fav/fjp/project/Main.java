package cz.fav.fjp.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cz.fav.fjp.project.logger.Logger;
import cz.fav.fjp.project.objects.FCompilationUnit;
import cz.fav.fjp.project.writer.CompilationUnitWriter;
import cz.fav.fjp.project.writer.WriterSettings;


public class Main {
	
	public static void main(String[] args) throws Exception {
		Logger.setLogLevel(10);
		Logger.setLogOutput(new OutputStreamWriter(new FileOutputStream(new File("log.log"))));
		
		WriterSettings.output = new OutputStreamWriter(new FileOutputStream(new File("out.c")));
		
		try {
			List<String> source = loadFromFile(args);
			if (source == null)
			{
				return;
			}
			source = Preprocessor.removeComments(source);
			source = Preprocessor.removeStrings(source);
			source = Preprocessor.removeImports(source);
			source = Preprocessor.removeEmptySpaces(source);
			
			List<String> words = Preprocessor.getWords(source);
			
			FCompilationUnit unit = new FCompilationUnit();
			unit.setWords(words);
			unit.setName(args[0]);
			unit.parse();
			
			CompilationUnitWriter wr = new CompilationUnitWriter();
			wr.transform(unit);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Logger.output.close();
			WriterSettings.output.close();
		}
		
	}
	
	private static List<String> loadFromFile(String[] args) throws IOException {
		if (args.length == 0)
		{
			System.out.println("You must specify the file you want to translate as a parameter.");
			return null;
		}
		String path = args[0];
		return FileUtils.readLines(new File(path));
	}

}

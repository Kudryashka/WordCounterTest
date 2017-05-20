import java.io.File;
import java.io.IOException;
import java.util.List;

import name.dimasik.wcounter.ArgumentsValidator;
import name.dimasik.wcounter.InvalidArgumentsException;
import name.dimasik.wcounter.WordCountPair;
import name.dimasik.wcounter.WordCounter;

/**
 * Entry point to the program.
 * 
 * Class name does not correspond java naming convention.
 * The reason is a requirement to call execution via <i>$java wordCount lyrics.txt 5</i>
 *
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 *
 */
public class wordCount {
	
	private static final String SUCCESS_OUT_FORMAT = "%s-%d";
	private static final String ERROR_OUT_FORMAT = "Execution error: \n    %s";

	/**
	 * Entry point method.
	 * This method validates an input and starts program execution.
	 */
	public static void main(String ... args) {
		try {
			//validate an input
			ArgumentsValidator.validate(args);
			
			//prepare arguments
			File file = new File(args[0]);
			int count = Integer.parseInt(args[1]);
			
			//process file processing		
			WordCounter counter = new WordCounter(file);
			counter.process();
			List<WordCountPair> topWords = counter.getTopCounts(count);
			
			//print execution result
			printResultAndExit(topWords);
		} catch(InvalidArgumentsException | IOException e) {
			//print error message and stop execution with an error code
			printErrorAndExit(e.getMessage());
		}
	}
	
	private static void printResultAndExit(List<WordCountPair> words) {
		for(WordCountPair pair : words) {
			System.out.println(String.format(SUCCESS_OUT_FORMAT, pair.word, pair.getCount()));
		}
		System.exit(0);
	}
	
	private static void printErrorAndExit(String msg) {
		System.out.println(String.format(ERROR_OUT_FORMAT, msg));
		System.exit(1);
	}
}

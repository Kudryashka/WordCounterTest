package name.dimasik.wcounter;

import java.io.File;

/**
 * Class for validation of the user input.
 *
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 *
 */
public final class ArgumentsValidator {

	/**
	 * Validate user input.
	 * @throws InvalidArgumentsException if the arguments don't correspond to the expected format.
	 */
	public static void validate(String ... args ) throws InvalidArgumentsException {
		//check is arguments exists
		if (args == null || args.length == 0) {
			throw new InvalidArgumentsException("Incorrect arguments format. There are no arguments.");
		}
		
		//check count of arguments 
		if (args.length != 2) {
			throw new InvalidArgumentsException("Incorrect arguments format. Illegal number of arguments.");
		}
		
		//check is file exists
		File file = new File(args[0]);
		if (!file.exists()) {
			throw new InvalidArgumentsException("Incorrect arguments format. File does not exists.");
		}
		
		//check is count is a positive number
		try {
			int count = Integer.parseInt(args[1]);
			if (count < 0) {
				throw new InvalidArgumentsException("Incorrect arguments format. Count can't be negative.");
			}
		} catch (NumberFormatException e) {
			throw new InvalidArgumentsException("Incorrect arguments format. Count is not a number.");
		}
		
		
	}
}

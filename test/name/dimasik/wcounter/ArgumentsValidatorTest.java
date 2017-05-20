package name.dimasik.wcounter;

import org.junit.Test;

/**
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 */
public class ArgumentsValidatorTest {

	private static final String TEST_FILE_NAME = "bin/test.txt";
	
	/*
	 * No exception expected
	 */
	@Test
	public void testCorrectArgument() throws InvalidArgumentsException {
		ArgumentsValidator.validate(TEST_FILE_NAME, "5");		
	}
	
	@Test(expected = InvalidArgumentsException.class)
	public void testNoArguments() throws InvalidArgumentsException {
		ArgumentsValidator.validate();
	}
	
	@Test(expected = InvalidArgumentsException.class)
	public void testIllegalArgumentsCount() throws InvalidArgumentsException {
		//Arguments count bigger then expected
		ArgumentsValidator.validate(TEST_FILE_NAME, "5", "illegal");
		//Arguments count lower then expected
		ArgumentsValidator.validate(TEST_FILE_NAME);
	}
	
	@Test(expected = InvalidArgumentsException.class)
	public void testIllegalCountArgument() throws InvalidArgumentsException {
		//Count is not a number
		ArgumentsValidator.validate(TEST_FILE_NAME, "foo");
		//Count is a negative number
		ArgumentsValidator.validate(TEST_FILE_NAME, "-5");
	}
}

package name.dimasik.wcounter;

/**
 * The class represents an exception that should be thrown to indicate illegal format of the user input.
 *
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 *
 */
public class InvalidArgumentsException extends Exception {

	private static final long serialVersionUID = 2735186912353092273L;

	public InvalidArgumentsException(String msg) {
		super(msg);
	}
}

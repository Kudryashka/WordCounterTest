package name.dimasik.wcounter;

/**
 * Represents pair of a word and a counter.
 *
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 *
 */
public class WordCountPair {

	/**
	 * The word.
	 */
	public final String word;
	
	private int counter;
	
	/**
	 * Create new pair for a specified word.
	 */
	public WordCountPair(String word) {
		this.word = word;
	}
	
	/**
	 * Increment counter by one.
	 */
	public void incrementCounter() {
		counter++;
	}
	
	/**
	 * Get the word.
	 * @return The word.
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Get counter value.
	 * @return counter value.
	 */
	public int getCount() {
		return counter;
	}
}

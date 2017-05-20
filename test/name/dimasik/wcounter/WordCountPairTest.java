package name.dimasik.wcounter;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 */
public class WordCountPairTest {
	
	@Test
	public void testZeroStartCounter() {
		WordCountPair pair = new WordCountPair("word");
		assertEquals("Pair should be initialized with zero counter value.", pair.getCount(), 0);
	}
	
	@Test
	public void testCounterIncrement() {
		WordCountPair pair = new WordCountPair("word");
		int before = pair.getCount();
		pair.incrementCounter();
		int after = pair.getCount();
		assertTrue("Incrementation of a counter shoul increment the counter by one.", after == before + 1);
	}
}

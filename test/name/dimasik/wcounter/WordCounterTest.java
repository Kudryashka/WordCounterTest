package name.dimasik.wcounter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 */
public class WordCounterTest {

	private static final String TEST_FILE_NAME = "bin/test.txt";
	
	private WordCounter counter;
	
	@Before
	public void init() {
		counter = new WordCounter(new File(TEST_FILE_NAME));
	}
	
	@Test
	public void testEmptyListWithoutProcess() {
		assertTrue("The list of results should be empty before file processing.", counter.getTopCounts(1).isEmpty());
	}
	
	@Test
	public void testExample() throws IOException {
		counter.process();
		List<WordCountPair> list = counter.getTopCounts(5);
		
		//check all five elements
		assertEquals("merry", list.get(0).word);
		assertEquals(16, list.get(0).getCount());
		
		assertEquals("christmas", list.get(1).word);
		assertEquals(8, list.get(1).getCount());
		
		assertEquals("their", list.get(2).word);
		assertEquals(8, list.get(2).getCount());
		
		assertEquals("to", list.get(3).word);
		assertEquals(8, list.get(3).getCount());
		
		assertEquals("and", list.get(4).word);
		assertEquals(6, list.get(4).getCount());
	}
	
	@Test
	public void testLineSplit() {
		String line = "w1 w2 w3, w4. w5,w6.w7  w8";
		String[] words = counter.splitLine(line);
		assertArrayEquals(words, new String[]{"w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8"});
	}
	
	@Test
	public void testProcessLine() {
		counter.processLine("Word1, word2. word2 word1 word1");
		List<WordCountPair> pairs = counter.getSortedPairs();
		
		assertEquals("Illegal result pairs count", 2, pairs.size());
		
		assertEquals("Wrong word in result list on first position", "word1", pairs.get(0).word);
		assertEquals("Wrong word in result list on second position", "word2", pairs.get(1).word);
		
		assertEquals("Wrong count for the first word", 3, pairs.get(0).getCount());
		assertEquals("Wrong count for the second word", 2, pairs.get(1).getCount());
	}

	@After
	public void destroy() {
		counter = null;
	}
}

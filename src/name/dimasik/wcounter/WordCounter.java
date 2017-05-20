package name.dimasik.wcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents an entity to calculate word counts in a file.
 *
 * @author Dmytro Kudria
 * @author <a href="http://dimasik.name">http://dimasik.name</a>
 *
 */
public class WordCounter {

	private final File file;
	private Map<String, WordCountPair> wordPairs = new HashMap<>();
	
	/**
	 * Create new instance for specified file.
	 * Assume file exists.
	 * 
	 * @param file text file with words.
	 */
	public WordCounter(File file) {
		this.file = file;
	}
	
	/**
	 * Process counting of words in the file.
	 * @throws IOException if any IO exception 
	 */
	public void process() throws IOException {
		wordPairs.clear();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				processLine(line);
			};
		} finally {
			if (reader != null) reader.close();
		}
	}
	
	/**
	 * Process counting for specified line.
	 * @param line The line.
	 */
	void processLine(String line) {
		String[] words = splitLine(line.toLowerCase());
		for (String word: words) {
			//Go to next iteration if word is empty
			if (word.isEmpty()) continue;
			//increment counters for every word in line
			WordCountPair pair = wordPairs.get(word);
			if (pair == null) {
				pair = new WordCountPair(word);
				wordPairs.put(word, pair);
			}
			pair.incrementCounter();
		}
	}
	
	/**
	 * Split line by the comma, dot or space characters.
	 * @param line The line.
	 * @return An array of words.
	 */
	String[] splitLine(String line) {
		return line.split("\\s*(,|\\.|\\s)\\s*");
	}
	
	/**
	 * Sort pairs by counter and natural ordering.
	 * @return Sorted pairs or empty list if no pairs.
	 */
	List<WordCountPair> getSortedPairs() {
		List<WordCountPair> pairs = new ArrayList<>(wordPairs.values());
		Collections.sort(pairs, new Comparator<WordCountPair>() {
			
			@Override
			public int compare(WordCountPair o1, WordCountPair o2) {
				//compaire by counter
				int count1 = o1.getCount();
				int count2 = o2.getCount();
				int result = count2 - count1;
				//compaire by words natural ordering if counter of both elements equals
				if (result == 0) {
					String w1 = o1.word;
					String w2 = o2.word;
					result = w1.compareTo(w2);
				}
				return result;
			}
		});
		return pairs;
	}
	
	/**
	 * Get most frequent words in a file.
	 * 
	 * @param top A number of words that should be returned.
	 * @return Sorted list of top frequent words or empty list if no words counted.
	 */
	public List<WordCountPair> getTopCounts(int top) {
		List<WordCountPair> pairs = getSortedPairs();
		if (pairs.size() > top) {
			return getSortedPairs().subList(0, top);
		} else {
			return pairs;
		}
	}
}

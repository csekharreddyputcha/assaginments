package search_term;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class WordSearchTest {

	private static final String DIRECTORY_PATH="D:\\testfiles";
	private static final String WORD="java";

	@Test
	void search_Word_Using_BruteForce() {
		long startTime = System.currentTimeMillis();
		String param="brute";
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		 
		WordSearch wordSearch = new WordSearch();
		HashMap<File, Long> wordCountMap = wordSearch.directoryIterate(directory,WORD, param);
		long endTime = System.currentTimeMillis();
		System.out.println("total time taken :" +  (endTime - startTime));
		assertEquals(wordCountMap.size(), 5);
	}
	
	@Test
	void search_Word_Using_RegEx() {
		long startTime = System.currentTimeMillis();
		String  param="regex";
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		 
		WordSearch wordSearch = new WordSearch();
		HashMap<File, Long> wordCountMap = wordSearch.directoryIterate(directory,WORD, param);
		long endTime = System.currentTimeMillis();
		System.out.println("total time taken :" +  (endTime - startTime));
		assertEquals(wordCountMap.size(), 5);
	}
	
	@Test
	void search_Word_In_File() {
		long startTime = System.currentTimeMillis();
		String  param="search";
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		 
		WordSearch wordSearch = new WordSearch();
		HashMap<File, Long> wordCountMap = wordSearch.directoryIterate(directory,WORD, param);
		long endTime = System.currentTimeMillis();
		System.out.println("total time taken :" +  (endTime - startTime));
		assertEquals(wordCountMap.size(), 5);

	}

}

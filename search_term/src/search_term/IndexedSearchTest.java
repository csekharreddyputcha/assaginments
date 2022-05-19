package search_term;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class IndexedSearchTest {
	
	private static final String DIRECTORY_PATH="D:\\testfiles";

	@Test
	void test() {
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		IndexedSearch search =  new IndexedSearch();
		search.processFiles(directory);
		HashMap<File, Map<String, Long>> filesIndexMap = search.fileIndexSearch();
		assertEquals(filesIndexMap.size(), 5);
		filesIndexMap.entrySet().forEach(entry -> System.out.println(entry.getKey().getName()+" :::- "+entry.getValue()));

	}

}

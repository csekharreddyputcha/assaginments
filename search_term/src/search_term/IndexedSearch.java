package search_term;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexedSearch {
	
	private HashMap<File, Map<String, Long>> filesIndexMap = new HashMap<>();
	private static final String DIRECTORY_PATH="D:\\testfiles";
	
	public HashMap<File, Map<String, Long>> fileIndexSearch(){
		return filesIndexMap;
	}
	
	
	public void processFiles(File filePath){
		
		HashMap<File, Map<String, Integer>> wordsIndex = new HashMap<>();
		
		File[] filesList = filePath.listFiles();
		for (File file : filesList) {
			if (file.isDirectory()) {
				processFiles(file);
			} else {
				processFile(file);
			}
		}
		
			
		
	}


	private void processFile(File file) {
		
		try {
			Map<String, Long> wordIndexMap = Files.lines(Paths.get(file.getCanonicalPath()))
				 .parallel()
				 .flatMap(line -> Arrays.stream(line.trim().split(" ")))
			     .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
			     .filter(word -> word.length() > 0)
			     .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
			
			filesIndexMap.put(file, wordIndexMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             
	}
	
	public static void main(String[] args) {
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		IndexedSearch search =  new IndexedSearch();
		search.processFiles(directory);
		search.filesIndexMap.entrySet().forEach(entry -> System.out.println(entry.getKey().getName()+" :::- "+entry.getValue()));

	}

}

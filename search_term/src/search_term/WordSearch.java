package search_term;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WordSearch {
	
	private static final String DIRECTORY_PATH="D:\\testfiles";
	private static final String WORD="java";
	HashMap<File, Long> wordCountMap = new HashMap<>();

	
	public static void main(String[] args) {
		String param = "regex";
		File directory = new File(DIRECTORY_PATH);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
		}
		 
		WordSearch wordSearch = new WordSearch();
		wordSearch.directoryIterate(directory,WORD,param);
	}
	
	public HashMap<File, Long> directoryIterate(File directory, String word, String  param){
		File[] filesList = directory.listFiles();
		for(File file : filesList) {
			if(!file.isFile() || file.isDirectory()) {
				directoryIterate(file,word,param);
			}
			else {
				if(param.equals("search")) {
					long count =searchWord(file,word);
					wordCountMap.put(file, count);
				}
				else if(param.equals("regex")) {
					long count =searchWordUsingRegEx(file, word);
					wordCountMap.put(file, count);
				}
				else if(param.equals("brute")) {
					long count=searchWordUsingBruteForce(file,word);
					wordCountMap.put(file, count);
				}
			}
		}
		
		return wordCountMap;
		
	}

	private long searchWord(File file, String word) {
		
		Scanner scanFile = null;
		long count=0;
		try {
			scanFile = new Scanner(new BufferedReader(new FileReader(file)));
	         count = scanFile.findAll(word).count();
	        
	        System.out.println(file.getName()+" - "+count);	    
		}catch(FileNotFoundException  ex) {
			ex.printStackTrace();
		}
		return count;
  	}
	
	private long searchWordUsingRegEx(File file, String word) {

		Scanner scanFile = null;
		long count=0;
		try {

			Pattern p = Pattern.compile("\\b" +word+ "\\b", Pattern.CASE_INSENSITIVE);
			scanFile = new Scanner(new BufferedReader(new FileReader(file)));
			count = scanFile.findAll(p).count();
			System.out.println(file.getName() + " - " + count);

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	private long searchWordUsingBruteForce(File file, String word) {

		long count=0;
		try {
		FileReader reader = new FileReader(file);
		char[] fileChars = new char[(int)file.length()];
		reader.read(fileChars);
		
		for(int i=0;i<(fileChars.length-word.length());i++) {
			
			int j;
			for( j=0;j<word.length();j++) {
				
				if(fileChars[i+j]!=word.charAt(j)) {
					break;
				}
			}
			if(j==word.length())
				count++;
			   
		}
		System.out.println(file.getName()+" - "+ count);

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return count;
	}

}

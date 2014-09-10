package com.FurnitureShop.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONObject;

import com.FurnitureShop.Model.DataHandler;

/**
 * This class based performs the Basic Serach operation process
 * @author Team Mavericks
 *
 */
public class BasicSearch {
	

	static HashSet<String> stopwords = new HashSet<String>();
	

	public static void main(String[] args) {
		
		String line="I need a black bedroom table";
		
		//System.out.println("The query string is :"+ line+"\n");

		String stopwordsFileLocation="D:\\Reading Zone\\MS\\Second Semester\\SE\\SEFurnitureShop\\ITERATION_2\\Database\\stopwords.txt";
		
		
		ArrayList resultReceived = new BasicSearch().words(line, stopwordsFileLocation);
		
		LinkedList<List<String>> stringCombinations=new BasicSearch().combine(resultReceived);
		LinkedList sortedList=new BasicSearch().sortLinkedList(stringCombinations, resultReceived.size());
		
		
		//System.out.println(new DataHandler().findItems(sortedList));
	}
	
	/**
	 * Main Method of Basic Search that receives the query String from user and does the processing. 
	 * @param Query
	 * @return JSONObject
	 */
	public JSONObject processBasicSearch(String Query)  			// kids desk
	{
		String stopwordsFileLocation="D:\\Reading Zone\\MS\\Second Semester\\SE\\SEFurnitureShop\\ITERATION_2\\Database\\stopwords.txt";
		
		ArrayList resultReceived = new BasicSearch().words(Query, stopwordsFileLocation);	//[kids, desk]
		
		LinkedList<List<String>> stringCombinations=new BasicSearch().combine(resultReceived);  //[[desk], [kids], [kids, desk]]
		
		LinkedList sortedList=new BasicSearch().sortLinkedList(stringCombinations, resultReceived.size()); //[[kids, desk], [desk], [kids]]
		
		return new DataHandler().findItems(sortedList);
	}
	
	/**
	 * This method reads the stopwords from the Text file and add them into the HashSet
	 * @param stopwordsFileLocation
	 */
	private void addStopwords(String stopwordsFileLocation){
		try{
			BufferedReader br = new BufferedReader(new FileReader(stopwordsFileLocation));
			while(br.ready()){
				stopwords.add(br.readLine());
			}
			br.close();
		}
		catch(Exception e){System.out.println(e);}
	}
	
	
	/**
	 * This Methods tries to add the words from the query string. Since it is HashSet so the StopWords will be eliminated
	 * @param line
	 * @param stopwordsFileLocation
	 * @return
	 */
	private ArrayList<String> words(String line, String stopwordsFileLocation){
		if(stopwords.size() == 0)
			addStopwords(stopwordsFileLocation);
		ArrayList<String> result = new ArrayList<String>();
 
		String[] words = line.split("[ \t\n,\\.\"!?$~()\\[\\]\\{\\}:;/\\\\<>+=%*]");
		for(int i=0; i < words.length; i++){
			if(words[i] != null && !words[i].equals("")){
				String word = words[i].toLowerCase();
				if(!stopwords.contains(word)){
					result.add(word);
					//result.add(Stemmer.stem(word));
				}
			}
		}
		return result;
	}
	
	/**
	 * This Sorts the input Linked List. The sorting is based on the number of keywords per set. This helps in prioritizing
	 * @param input
	 * @param length
	 * @return Sorted LinkedList
	 */
	private LinkedList sortLinkedList(LinkedList input, int length)
	{
		ListIterator iterator;
		LinkedList sortedList = new LinkedList();
		LinkedList individualComponent;
		
		for(int i=length; i>0; i--)
		{
			iterator = input.listIterator();
			while(iterator.hasNext())
			{
				individualComponent = (LinkedList) iterator.next();
				if(individualComponent.size()==i)
				{
					sortedList.add(individualComponent);
				}
			}
		}
		return sortedList;
	}
	
	/**
	 * This MEthod find all combination of the keywords identifed after removing the stopwords. After this, the list is sorted by the sortLinkedList Funciton
	 * @param originalList
	 * @return LinkedList
	 */
    private LinkedList<List<String>> combine(ArrayList originalList ){
    	
    		    LinkedList<List<String>> combinations = new LinkedList<List<String>>();
    		     
    		    int size = originalList.size();
    		     
    		    int threshold = Double.valueOf(Math.pow(2, size)).intValue() - 1; 
    		     
    		    for (int i = 1; i <= threshold; ++i) {
    		      LinkedList<String> individualCombinationList = new LinkedList<String>();
    		      int count = size - 1;
    		       
    		      int clonedI = i;
    		      while (count >= 0) {
    		        if ((clonedI & 1) != 0) {
    		          individualCombinationList.addFirst((String) originalList.get(count));
    		        }
    		         
    		        clonedI = clonedI >>> 1;
    		        --count;
    		      }
    		       
    		      combinations.add(individualCombinationList);
    		 
    		    }
    		     
    		    return combinations;
    		  }
    	
    }

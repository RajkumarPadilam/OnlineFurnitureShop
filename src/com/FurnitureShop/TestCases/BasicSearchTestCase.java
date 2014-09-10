package com.FurnitureShop.TestCases;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.FurnitureShop.Controller.BasicSearch;
import com.FurnitureShop.Model.DataHandler;
import com.FurnitureShop.Utilities.Constants;

public class BasicSearchTestCase {


	static BasicSearch handler;
	Constants Constant= Constants.getInstance();
	
	@BeforeClass
	public static void createHandlerObject() {
		
		handler=new BasicSearch();
	}
	
	@AfterClass
	public static void releaseHandlerObject() {
		handler =null;
	}
	
	@Test
	public void testMethodForGetItemDetails() {
		
		JSONObject json = handler.processBasicSearch("Kids Desk");
		JSONArray SearchResultsReceived = (JSONArray) json.get("ITEMS");
		
		//System.out.println(json+" :"+ItemReceived.size());
		
		int ExpectedCountforSearchResults = 12;
		
		int ReceivedCountforSearchResults = SearchResultsReceived.size();
		
	
		assertEquals("1",json.get(Constant.SUCCESS));
		assertEquals("Successful", json.get(Constant.MESSAGE));
		
		assertEquals(ExpectedCountforSearchResults, ReceivedCountforSearchResults);
		
	}
}

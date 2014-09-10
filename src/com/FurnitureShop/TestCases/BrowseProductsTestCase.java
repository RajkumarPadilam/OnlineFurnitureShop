package com.FurnitureShop.TestCases;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.FurnitureShop.Model.DataHandler;
import com.FurnitureShop.Utilities.Constants;

public class BrowseProductsTestCase {

	static DataHandler handler;
	Constants Constant= Constants.getInstance();

	@BeforeClass
	public static void createHandlerObject() {
		
		handler=new DataHandler();
	}
	
	@AfterClass
	public static void releaseHandlerObject() {
		handler =null;
	}
	
	@Test
	public void testMethodForGetCategories() {
		
		JSONObject json = handler.getCategory();
	
		int noOfCategoriesExpected = 6;
		
		JSONArray s=(JSONArray) json.get("CATEGORIES");		
		int noOfCategoriesReceived = s.size();
		
		
		
		assertEquals(1,json.get(Constant.SUCCESS));
		assertEquals("Successful", json.get(Constant.MESSAGE));
		assertEquals(noOfCategoriesExpected, noOfCategoriesReceived);
		
	}
	
	@Test
	public void testMethodForGetSubCategories() {
		
		JSONObject json = handler.getSubCategory("Living room Furniture");
	
		int noOfSubCategoriesExpected = 13;
		
		JSONArray s=(JSONArray) json.get("SUBCATEGORIES");		
		int noOfSubCategoriesReceived = s.size();
		
		
		
		assertEquals(1,json.get(Constant.SUCCESS));
		assertEquals("Successful", json.get(Constant.MESSAGE));
		assertEquals(noOfSubCategoriesExpected, noOfSubCategoriesReceived);
		
	}
	
	@Test
	public void testMethodForGetItemURLs() {
		
		JSONObject json = handler.getItemURLS("Living room Furniture", "Chairs");
	
		
		
		int noOfItemURLsExpected = 4;
		
		JSONArray s=(JSONArray) json.get("ITEMS");		
		int noOfItemUrlsReceived = s.size();
		
		
		
		assertEquals(1,json.get(Constant.SUCCESS));
		assertEquals("Successful", json.get(Constant.MESSAGE));
		assertEquals(noOfItemURLsExpected, noOfItemUrlsReceived);
		
	}
	
	
	
}

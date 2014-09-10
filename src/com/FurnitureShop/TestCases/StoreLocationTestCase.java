package com.FurnitureShop.TestCases;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.FurnitureShop.Model.DataHandler;
import com.FurnitureShop.Utilities.Constants;

public class StoreLocationTestCase {


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
	public void testMethodForGetItemDetails() {
		
		JSONObject json = handler.getItemDetails("http://ak1.ostkcdn.com/images/products/4231879/Columbus-Queen-Premier-Mattress-Futon-Set-P12223577a.jpg");
		JSONObject ItemReceived = (JSONObject) json.get("ITEM");
		
		
		int ExpectedStoreLocation =2;
		
		int ReceivedStoreLocation = Integer.parseInt((String) ItemReceived.get("STORE_LOCATION"));
		
		assertEquals(1,json.get(Constant.SUCCESS));
		assertEquals("Successful", json.get(Constant.MESSAGE));
		
		
		assertEquals(ExpectedStoreLocation, ReceivedStoreLocation);
		
		
	}
	
	
}

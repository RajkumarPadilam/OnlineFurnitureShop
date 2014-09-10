package com.FurnitureShop.Controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.FurnitureShop.Model.DataHandler;
import com.FurnitureShop.Utilities.Constants;

/**
 * This class analyses the TAG and calls the respective method of DataHandler for further processing.
 * @author Team Mavericks
 *
 */
public class TagHandler {
	
	
	JSONObject json=new JSONObject();
	DataHandler dbHandler=new DataHandler();
	
	Constants Constant= Constants.getInstance();
	
	/**
	 * Processes the TAG received from client and then calls the appropriate method of the Model DataHandler
	 * @param TAG
	 * @param HTTPrequest
	 * @return JSONObject
	 */
	public JSONObject processTAG(String TAG, HttpServletRequest request)
	{	
		
		if(TAG.equals(Constant.GETCATEGORIES))
		{
			json=dbHandler.getCategory();
		}
		
		else	if(TAG.equals(Constant.GETSUBCATEGORIES))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			if(CategoryName.contains("Dining"))
				CategoryName="Dining room & Bar Furniture";
			if(CategoryName.contains("Home"))
				CategoryName="Home office Furniture";
			
			json=dbHandler.getSubCategory(CategoryName);
		}
		else if(TAG.equals(Constant.GETCRITERIA))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			String SubCategoryName=request.getParameter("SUBCATEGORY_NAME");
			
			json=dbHandler.getCriterias(CategoryName, SubCategoryName);
		}
		else if(TAG.equals(Constant.GETVALUES))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			String SubCategoryName=request.getParameter("SUBCATEGORY_NAME");
			String CriteriaName=request.getParameter("CRITERIA_NAME");
			
			json=dbHandler.getValuesForCriteria(CategoryName, SubCategoryName, CriteriaName);
		}
		else if(TAG.equals(Constant.ADVANCED_SEARCH))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			String SubCategoryName=request.getParameter("SUBCATEGORY_NAME");
			String input=request.getParameter("INPUT");
			String sort=request.getParameter("SORT");
			
			
			json=dbHandler.getItemURLS(CategoryName, SubCategoryName, input, sort, "nothing");
		}
		else if(TAG.equals(Constant.GETITEMS))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			String SubCategoryName=request.getParameter("SUBCATEGORY_NAME");
			if(CategoryName.contains("Dining"))
				CategoryName="Dining room & Bar Furniture";
			if(CategoryName.contains("Home"))
				CategoryName="Home office Furniture";
			
			json=dbHandler.getItemURLS(CategoryName, SubCategoryName);
		}
		else if(TAG.equals(Constant.GETITEMSFORCATEGORY))
		{
			String CategoryName=request.getParameter("CATEGORY_NAME");
			if(CategoryName.contains("Dining"))
				CategoryName="Dining room & Bar Furniture";
			if(CategoryName.contains("Home"))
				CategoryName="Home office Furniture";
			
			json=dbHandler.getItemURLS(CategoryName);
		}
		else if(TAG.equals(Constant.BASIC_SEARCH))
		{
			String searchString=request.getParameter("SEARCH_STRING");
			
	//		json=dbHandler.findItems(searchString);
			json=new BasicSearch().processBasicSearch(searchString);
			
		}else if(TAG.equals(Constant.GETITEMDETAILS))
		{
			String url=request.getParameter("ITEM_URL");
			
			json=dbHandler.getItemDetails(url);
		}
		return json;
	}
	
	public JSONObject processTAG(String TAG)
	{	
		
		if(TAG.equals(Constant.GETSUBCATEGORIES))
		{
			json=dbHandler.getSubCategory("Living room Furniture");
		}
		return json;
	}
		
}

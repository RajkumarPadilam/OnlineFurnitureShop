package com.FurnitureShop.Utilities;

/**
 * This class based on the Single Pattern, serves as the resource for all Constants used in the application
 * @author Team Mavericks
 *
 */
public class Constants {
    
  //TAG NAMES
  	public final String GETCATEGORIES="getCategories";
  	public final String GETSUBCATEGORIES="getSubCategories";
  	public final String GETVALUES="getValues";
  	public final String GETCRITERIA="getCriteria";
  	public final String GETITEMS="getItems";
  	public final String GETITEMDETAILS="getItemDetails";
  	public final String GETITEMSFORCATEGORY="getItemsForCategory";
  	public final String GETITEMSFORCATANDSUBCATEGORY="getItemsForCategoryAndSubcategory";
  	public final String BASIC_SEARCH="basicSearch";
  	public final String ADVANCED_SEARCH="advancedSearch";
  	
  	//PARAMETER NAMES
  	public final String SUCCESS="success";
  	public final String MESSAGE="msg";

  	private static volatile Constants Constant;
  	
  	private Constants()
  	{}
    
  	public static Constants getInstance()
  	{
  		if(Constant == null)
  		{
  			synchronized(Constants.class)
  			{
  				if(Constant == null)
  				{
  					Constant= new Constants();
  				}
  			}
  		}
  		return Constant;
  	}
}

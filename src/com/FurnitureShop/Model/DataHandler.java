package com.FurnitureShop.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FurnitureShop.Utilities.Constants;

/**
 * This class has functions that interact with the database, fetch relevant data and forward it back to the controller
 * @author Team Mavericks
 *
 */
public class DataHandler {

	Connection connection;
	DBConnect dbObject=new DBConnect();
	JSONObject json;
	
	Constants Constant= Constants.getInstance();
	
	/**
	 * Gets the different Categories present in the database
	 * @return JSONObject
	 */
	public JSONObject getCategory()
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="Select CATEGORY_ID,CATEGORY_NAME FROM CATEGORY";
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray categories=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("CATEGORY", rs.getString("CATEGORY_NAME"));
					jsonObject.put("CATEGORY_ID", rs.getString("CATEGORY_ID"));
					categories.add(jsonObject);
				}
				
				json.put("CATEGORIES", categories);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Gets the different SubCategories present in the database
	 * @param CategoryID
	 * @return JSONObject
	 */
	public JSONObject getSubCategory(int CategoryID)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="Select SUBCATEGORY_ID, SUBCATEGORY_NAME FROM SUBCATEGORY";
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray subCategories=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("SUBCATEGORY", rs.getString("SUBCATEGORY_NAME"));
					jsonObject.put("SUBCATEGORY_ID", rs.getString("SUBCATEGORY_ID"));
					subCategories.add(jsonObject);
				}
				
				json.put("SUBCATEGORIES", subCategories);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Gets the different SubCategories present in the database
	 * @param Category_Name
	 * @return JSONObject
	 */
	public JSONObject getSubCategory(String Category_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select SUBCATEGORY_ID, SUBCATEGORY_NAME FROM SUBCATEGORY AS S, CATEGORY AS C WHERE C.CATEGORY_NAME='"+Category_Name+"' and  C.CATEGORY_ID=S.CATEGORY_ID";
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray subCategories=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("SUBCATEGORY", rs.getString("SUBCATEGORY_NAME"));
					jsonObject.put("SUBCATEGORY_ID", rs.getString("SUBCATEGORY_ID"));
					subCategories.add(jsonObject);
				}
				
				json.put("SUBCATEGORIES", subCategories);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Gets the different criterias present in the Database for a item
	 * @param Category_Name
	 * @param SubCategory_Name
	 * @return JSONObject
	 */
	public JSONObject getCriterias(String Category_Name, String SubCategory_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select CRITERIA_ID, CRITERIA_NAME FROM CRITERIA AS CR, SUBCATEGORY AS S, "
					+ "CATEGORY AS C WHERE C.CATEGORY_NAME='"+Category_Name+"' AND S.SUBCATEGORY_NAME='"+SubCategory_Name+"' AND  "
					+ "C.CATEGORY_ID=CR.CATEGORY_ID AND S.SUBCATEGORY_ID=CR.SUBCATEGORY_ID LIMIT 4";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray criterias=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("CRITERIA", rs.getString("CRITERIA_NAME"));
					criterias.add(jsonObject);
				}
				
				json.put("CRITERIAS", criterias);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject getValuesForCriteria(String Category_Name, String SubCategory_Name, String Criteria_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select VALUE_ID, VALUE_NAME FROM VALUE AS V,CRITERIA AS CR, SUBCATEGORY AS S, CATEGORY AS C WHERE C.CATEGORY_NAME='"+Category_Name+"' AND S.SUBCATEGORY_NAME='"+SubCategory_Name+"' AND  CR.CRITERIA_NAME='"+Criteria_Name+"'AND C.CATEGORY_ID=V.CATEGORY_ID AND S.SUBCATEGORY_ID=V.SUBCATEGORY_ID AND CR.CRITERIA_ID=V.CRITERIA_ID";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				json.put("CRITERIA", Criteria_Name);
				
				JSONArray values=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("VALUE", rs.getString("VALUE_NAME"));
					values.add(jsonObject);
				}
				
				json.put("VALUES", values);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Gets the URLS of items present in the database for a Category
	 * @param Category_Name
	 * @return JSONObject
	 */
	public JSONObject getItemURLS(String Category_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select ITEM_URL FROM ITEMS I, CATEGORY C WHERE C.CATEGORY_ID=I.CATEGORY_ID AND C.CATEGORY_NAME='"+Category_Name+"' LIMIT 4";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray items=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
					items.add(jsonObject);
				}
				
				json.put("ITEMS", items);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Gets the URLS of items present in the database for a Category
	 * @param Category_Name
	 * @param SubCategory_Name
	 * @return JSONObject
	 */
	public JSONObject getItemURLS(String Category_Name, String SubCategory_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select ITEM_URL FROM ITEMS I, CATEGORY C, SUBCATEGORY S WHERE C.CATEGORY_ID=I.CATEGORY_ID"
					+ " AND S.SUBCATEGORY_ID=I.SUBCATEGORY_ID AND "
					+ "C.CATEGORY_NAME='"+Category_Name+"' AND S.SUBCATEGORY_NAME='"+SubCategory_Name+"' LIMIT 4";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray items=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
					items.add(jsonObject);
				}
				
				json.put("ITEMS", items);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject getItemURLS(String Category_Name, String SubCategory_Name, String Criteria_Name, String Value_Name)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select ITEM_URL,PRICE FROM ITEMS I, CATEGORY C, SUBCATEGORY S, CRITERIA CR, VALUE V WHERE C.CATEGORY_ID=I.CATEGORY_ID AND "
					+ "S.SUBCATEGORY_ID=I.SUBCATEGORY_ID AND CR.CRITERIA_ID=I.CRITERIA_ID AND V.VALUE_ID=I.VALUE_ID AND "
					+ "CR.CRITERIA_NAME='"+Criteria_Name+"' AND V.VALUE_NAME='"+Value_Name+"' AND C.CATEGORY_NAME='"+Category_Name+"' AND S.SUBCATEGORY_NAME='"+SubCategory_Name+"' LIMIT 4;";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray items=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("PRICE", rs.getString("PRICE"));
					jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
					items.add(jsonObject);
				}
				
				json.put("COUNT", items.size());
				json.put("ITEMS", items);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject getItemURLS(String Category_Name, String SubCategory_Name, String input, String sort, String nothing)
	{
		
			StringTokenizer tokenizer=new StringTokenizer(input,",");
			
			JSONObject finalJSON=new JSONObject();
			finalJSON.put(Constant.SUCCESS, 0);
			
			JSONArray items=new JSONArray();
			JSONObject jsonObject;	
			
			while(tokenizer.hasMoreTokens())
			{
				finalJSON.put(Constant.SUCCESS, 1);
				finalJSON.put(Constant.MESSAGE, "Successful");
				
				String pair = tokenizer.nextToken();
				String CriteriaName = pair.substring(0, pair.indexOf(':'));
				String ValueName = pair.substring(pair.indexOf(':')+1,pair.length());
				
				JSONObject itemsFound = getItemURLS(Category_Name, SubCategory_Name, CriteriaName, ValueName);
				JSONArray itemsArray = (JSONArray) itemsFound.get("ITEMS");
				for(int i=0;i<itemsArray.size();i++)
				{
					jsonObject=new JSONObject();
					
					JSONObject myjson= (JSONObject) itemsArray.get(i);
	
					jsonObject.put("ITEM_CRITERIA", CriteriaName+" : "+ValueName);
					jsonObject.put("PRICE", myjson.get("PRICE"));
					jsonObject.put("ITEM_URL", myjson.get("ITEM_URL"));
					
					items.add(jsonObject);
				}			
				
			}
			
			if(sort.contains("ASC"))
			items = sortArray(items,1);
			else
			items = sortArray(items,2);
			
			finalJSON.put("COUNT", items.size());
			finalJSON.put("ITEMS", items);
			//System.out.println(fina);
			
			return finalJSON;
	}
	
	public JSONArray sortArray(JSONArray items, int sort)
	{
		for(int i=0;i<items.size();i++)
		{
			//JSONObject obj1=(JSONObject) items.get(i);
			
			for(int j=1;j<items.size()-i;j++)
			{
				JSONObject obj1=(JSONObject) items.get(j-1);
				JSONObject obj2=(JSONObject) items.get(j);
				if(sort==1)
				{
					if(Integer.parseInt((String) obj1.get("PRICE")) > Integer.parseInt((String) obj2.get("PRICE")))
					{
						items.set(j-1, obj2);
						items.set(j, obj1);
					}
				}else
				{
					if(Integer.parseInt((String) obj1.get("PRICE")) < Integer.parseInt((String) obj2.get("PRICE")))
					{
						items.set(j-1, obj2);
						items.set(j, obj1);
					}
				}
				
			}
		}
		return items;
	 }
	
	

	/**
	 * Performs the Basic Search Operation. Takes LinkedList of Keywords and returns relevant items from the database 
	 * @param searchKeyWords
	 * @return JSONObject
	 */
	public JSONObject findItems(LinkedList searchKeyWords)
	{	
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			int count=0;
			json=new JSONObject();
			json.put(Constant.SUCCESS, "0");
			
			JSONArray items=new JSONArray();
			
			ListIterator list3=searchKeyWords.listIterator();
			while(list3.hasNext())
			{
				
				LinkedList individualComponents=(LinkedList) list3.next();
				System.out.println(individualComponents);
				
				ListIterator list4=individualComponents.listIterator();
				String sql="select ITEM_URL from ITEMS2 WHERE ";
				
				while(list4.hasNext())
				{
					String keyword=(String) list4.next();
					sql=sql+" (ITEM_DESCRIPTION LIKE '%"+keyword+"%' OR ITEM_URL LIKE '%"+keyword+"%') AND ";
					//sql=sql+" ITEM_DESCRIPTION LIKE '"+keyword+"%' AND ";
				}
				sql=sql.substring(0, sql.length()-4);
				sql=sql + " LIMIT 4";
				System.out.println(sql);
			
				
				ResultSet rs=stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst())
				{
					json.put(Constant.SUCCESS, "1");
					json.put(Constant.MESSAGE, "Successful");
			
					JSONObject jsonObject;			
					while(rs.next())
					{
						jsonObject=new JSONObject();
						jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
						jsonObject.put("ITEM_CRITERIA", individualComponents);
						items.add(jsonObject);
						count++;
					}
				}
			 
			}
					
			HashSet<String> removeRedundantItems=new HashSet<String>();
			for(int i=0;i<items.size();i++)
			{
				JSONObject j=(JSONObject) items.get(i);
				if(!removeRedundantItems.add((String) j.get("ITEM_URL")))
					items.remove(i);
			}
			
					// end of while loop
					json.put("ITEMS", items);
					json.put("COUNT", count);
		 } 
			catch (ClassNotFoundException | SQLException e) 
			{
				e.printStackTrace();
			}
		return json;
	}
	
	public JSONObject findItems(String searchString)
	{
		String string1="";
		String string2="";
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select ITEM_URL from ITEMS WHERE ITEM_URL LIKE '%"+searchString+"%' LIMIT 4";
			
			StringTokenizer st=new StringTokenizer(searchString," ");
			if(st.hasMoreTokens())
			{
				string1=st.nextToken().trim();
				sql="select ITEM_URL from ITEMS WHERE ITEM_URL LIKE '%"+string1+"%' LIMIT 4";
			}
				
			if(st.hasMoreTokens())
			{
				string2=st.nextToken().trim();
				sql="select ITEM_URL from ITEMS WHERE ITEM_URL LIKE '%"+string1+"%"+string2+"%' OR ITEM_URL LIKE '%"+string2+"%"+string1+"%' LIMIT 4";
			}
			
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONArray items=new JSONArray();
				JSONObject jsonObject;			
				while(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
					items.add(jsonObject);
				}
				
				json.put("ITEMS", items);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
		
	}
	
	/**
	 * Returns the Product details. Returns the ItemDescription, Availability and Store location for an item form database
	 * @param ITEM_URL
	 * @return JSONObject
	 */
	public JSONObject getItemDetails(String ITEM_URL)
	{
		try
		{
			connection=dbObject.getConnectionToDatabase();
			Statement stmt=connection.createStatement();
			
			String sql="select ITEM_URL, ITEM_DESCRIPTION, STORE_LOCATION, AVAILABILITY FROM ITEMS where ITEM_URL='"+ITEM_URL+"' LIMIT 1";
			
			ResultSet rs=stmt.executeQuery(sql);
			
			json=new JSONObject();
			json.put(Constant.SUCCESS, 0);
			
			if(rs.isBeforeFirst())
			{
				json.put(Constant.SUCCESS, 1);
				json.put(Constant.MESSAGE, "Successful");
				
				JSONObject jsonObject = null;			
				if(rs.next())
				{
					jsonObject=new JSONObject();
					jsonObject.put("ITEM_URL", rs.getString("ITEM_URL"));
					jsonObject.put("ITEM_DESCRIPTION", rs.getString("ITEM_DESCRIPTION"));
					jsonObject.put("STORE_LOCATION", rs.getString("STORE_LOCATION"));
					jsonObject.put("AVAILABILITY", rs.getString("AVAILABILITY"));
				}
				
				json.put("ITEM", jsonObject);
			}			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	
}

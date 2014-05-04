/**
 * Model for OLAP operations.
 * Constructs a SQL statement from the specified options 
 * the user has selected in the view.
 * 
 * CS 157B - Spring 2014
 * @author Kevin Tan
 * @version 2 - May 4, 2014
 */

import static java.util.Arrays.asList;
import java.util.List;

public class DatabaseModel 
{
	public static String SQL = ""; // The SQL statement to be executed.
	public static String fact_table_name = "sales_fact"; // Identify the fact table name.
	
	/**
	 * Constructs a SQL statement.
	 * @param dimensions the dimensions selected. 
	 * @param attributes the attributes selected.
	 * @param fact_attributes the attributes of the fact table.
	 * @return SQL the SQL statement that will be executed.
	 */
	public static String constructSQL(List<String> dimensions, 
									  List<String> attributes,
									  List<String> fact_attributes)
	{	
		SQL += "SELECT";
		for (String s : attributes) // Gets the selected attributes.
			SQL += " " + s + ",";
		for (String s : fact_attributes) // Gets the fact table attributes and wraps with sum().
			SQL += " sum(" + s + ") as \""+ s + "\",";
		SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
		
		SQL += " FROM";
		SQL += " " + fact_table_name + ","; // Always include the fact table.
		for (String s : dimensions) // Gets the selected dimensions.
			SQL += " " + s + ",";
		SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
		
		SQL += " WHERE";
		// Assumes that primary keys will be the format "[dimension_name]_key"
		for (String s : dimensions)
			SQL += " " + fact_table_name + "." + s + "_key = " + s + "." + s + "_key AND";
		SQL = removeLastChar(SQL, 4); // Remove the trailing AND.
		
		SQL += " GROUP BY";
		for (String s : attributes) // Gets the selected attributes.
			SQL += " " + s + ",";
		SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
		
		return SQL;
	}
	
	/**
	 * Removes the last character(s) of a string.
	 * @param s the string where the last character(s) will be removed.
	 * @param i the amount of characters to remove.
	 * @return string with the last character removed.
	 */
	private static String removeLastChar(String s, int i) 
	{
        return s.substring(0, s.length() - i);
    }
	
	/**
	 * For testing purposes.
	 * @param args not used.
	 */
	public static void main(String[] args) 
	{	
		List<String> dimensions = asList("product", "promotion");
		List<String> attributes = asList("brand", "weight", "ad_type");
		List<String> fact_attributes = asList("dollar_sales");
		
		System.out.println(constructSQL(dimensions, attributes, fact_attributes));
	}

}

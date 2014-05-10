/**
 * Main frame that holds all of the panels, buttons, and table.
 * 
 * CS 157B - Spring 2014
 * @author Vinh Doan, Farjahan Hossain, Kevin Tan
 */
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseFrame extends JFrame{

	private static Connection connection;
	private static PreparedStatement preparedStatement = null;
	
	public static DatabaseModel model;
	
	JPanel leftPanel,rightPanel;
	static JPanel tablePanel;
	
	DimensionPanel store;
	DimensionPanel time;
	DimensionPanel product;
	ArrayList<DimensionPanel> dimensionList;
	
	// Variables used to construct the SQL statement.
	public static boolean store_name = false;
	public static boolean store_number = false;
	public static String store_location, store_sqft;
	
	public DatabaseFrame(final Connection conn, DatabaseModel model)
	{
		connection = conn;
		this.model = model;
		dimensionList = new ArrayList<DimensionPanel>();
		setLayout(new GridLayout(1,2));
		onCreate();
		populateDimension();
		
	}
	
	public void onCreate()
	{
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200,200));
		store = new DimensionPanel("store");
		time = new DimensionPanel("time");
		product = new DimensionPanel("product");
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(store);
		leftPanel.add(time);
		leftPanel.add(product);
		
		dimensionList.add(store);
		dimensionList.add(time);
		dimensionList.add(product);
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2,1));
		FactPanel fact = new FactPanel();
		fact.linkDimension(dimensionList);
		rightPanel.add(fact);
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(0,1));
		
		add(leftPanel);
		add(rightPanel);
		rightPanel.add(tablePanel);
		
		try {
			updateTable("SELECT * FROM time");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void populateDimension()
	{

		ArrayList<String> firstStoreAttribute  = new ArrayList<String>();
		firstStoreAttribute.add("name");
		firstStoreAttribute.add("store_number");
		store.addAttributes("Store",false, firstStoreAttribute);
		
		ArrayList<String> secondStoreAttribute  = new ArrayList<String>();
		secondStoreAttribute.add("store_street_address");
		secondStoreAttribute.add("store_zip");
		secondStoreAttribute.add("city");
		secondStoreAttribute.add("store_county");
		secondStoreAttribute.add("store_state");
		store.addAttributes("Store Location",true, secondStoreAttribute);
		
		ArrayList<String> thirdStoreAttribute  = new ArrayList<String>();
		thirdStoreAttribute.add("meat_sqft");
		thirdStoreAttribute.add("frozen_sqft");
		thirdStoreAttribute.add("grocery_sqft");
		thirdStoreAttribute.add("store_sqft");
		store.addAttributes("Store Sqft",true, thirdStoreAttribute);
		
		
		ArrayList<String> firstProductAttribute  = new ArrayList<String>();
		firstProductAttribute.add("SKU_number");
		product.addAttributes("Product",false,firstProductAttribute);
		
		ArrayList<String> secondProductAttribute  = new ArrayList<String>();
		secondProductAttribute.add("description");
		secondProductAttribute.add("full_description");
		product.addAttributes("Product Description",true,secondProductAttribute);
		
		ArrayList<String> thirdProductAttribute  = new ArrayList<String>();
		thirdProductAttribute.add("brand");
		thirdProductAttribute.add("subcategory");
		thirdProductAttribute.add("category");
		thirdProductAttribute.add("department");
		product.addAttributes("Product Section",true,thirdProductAttribute);
		
		ArrayList<String> fourthProductAttribute  = new ArrayList<String>();
		fourthProductAttribute.add("units_per_retail_case");
		fourthProductAttribute.add("units_per_shipping_case");
		fourthProductAttribute.add("cases_per_pallet");
		product.addAttributes("Product Unit",true,fourthProductAttribute);
	
			
		ArrayList<String> firstTimeAttribute  = new ArrayList<String>();	
		firstTimeAttribute .add("date");
		time.addAttributes("Time", false, firstTimeAttribute);
		
		ArrayList<String> secondTimeAttribute  = new ArrayList<String>();
		secondTimeAttribute.add("day_of_week");
		secondTimeAttribute.add("week_number_in_year");
		secondTimeAttribute.add("week_number_overall");
		time.addAttributes("Time Week", true, secondTimeAttribute);
		
		ArrayList<String> thirdTimeAttribute  = new ArrayList<String>();
		thirdTimeAttribute.add("day_number_in_month");
		thirdTimeAttribute.add("Month");
		thirdTimeAttribute.add("quarter");
		thirdTimeAttribute.add("year");
		time.addAttributes("Time Year", true, thirdTimeAttribute);
	}
	
	/**
	 * Builds a table model
	 * @author Paul Vargas on http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
	 * @param rs the result set
	 * @return a table model with the data and column names
	 * @throws SQLException
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException 
	{
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }


	/**
	 * Updates the table with a new ranking category
	 * @param statement the SQL query to be executed
	 * @throws SQLException
	 */
	public static void updateTable(String statement) throws SQLException
	{
		tablePanel.removeAll(); // first clear the existing table

		preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
	    JTable table = new JTable(buildTableModel(resultSet));

	    tablePanel.add(new JScrollPane(table)); // add the new table to the panel

	    tablePanel.repaint();
	    tablePanel.revalidate();
	}
	
}

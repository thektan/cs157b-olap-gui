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

public class DatabaseFrame extends JFrame
{
	private static Connection connection;
	private static PreparedStatement preparedStatement = null;
	
	public static DatabaseModel model;
	
	JPanel leftPanel,rightPanel;
	static JPanel tablePanel;
	
	DimensionPanel store;
	DimensionPanel time;
	DimensionPanel product;
	DimensionPanel promotion;
	ArrayList<DimensionPanel> dimensionList;
	
	// Variables used to construct the SQL statement.
	public static boolean store_name = false;
	public static boolean store_number = false;
	public static String store_location, store_sqft;
	
	/**
	 * Constructs the database frame. 
	 * @param conn the connection to the database.
	 * @param model the model of the application.
	 */
	public DatabaseFrame(final Connection conn, DatabaseModel model)
	{
		connection = conn;
		this.model = model;
		dimensionList = new ArrayList<DimensionPanel>();
		setLayout(new GridLayout(1,2));
		onCreate();
		populateDimension();
	}
	
	/**
	 * Creates the panels and other elements for the frame.
	 */
	public void onCreate()
	{
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200,200));
		store = new DimensionPanel("store");
		time = new DimensionPanel("time");
		product = new DimensionPanel("product");
		promotion = new DimensionPanel("promotion");
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(store);
		leftPanel.add(time);
		leftPanel.add(product);
		leftPanel.add(promotion);
		
		dimensionList.add(store);
		dimensionList.add(time);
		dimensionList.add(product);
		dimensionList.add(promotion);
		
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
	}
	
	/**
	 * Inserts in the dimensions and defines the attribute groups.
	 */
	public void populateDimension()
	{
		// Store Dimension
		ArrayList<String> firstStoreAttribute  = new ArrayList<String>();
		firstStoreAttribute.add("name");
		firstStoreAttribute.add("store_number");
		firstStoreAttribute.add("store_manager");
		firstStoreAttribute.add("store_phone");
		firstStoreAttribute.add("store_FAX");
		firstStoreAttribute.add("floor_plan_type");
		firstStoreAttribute.add("photo_processing_type");
		firstStoreAttribute.add("finance_services_type");
		firstStoreAttribute.add("first_opened_date");
		firstStoreAttribute.add("last_remodel_date");
		store.addAttributes("Store",false, firstStoreAttribute);
		
		ArrayList<String> secondStoreAttribute  = new ArrayList<String>();
		secondStoreAttribute.add("store_street_address");
		secondStoreAttribute.add("store_zip");
		secondStoreAttribute.add("city");
		secondStoreAttribute.add("sales_district");
		secondStoreAttribute.add("sales_region");
		secondStoreAttribute.add("store_county");
		secondStoreAttribute.add("store_state");
		store.addAttributes("Store Location",true, secondStoreAttribute);
		
		ArrayList<String> thirdStoreAttribute  = new ArrayList<String>();
		thirdStoreAttribute.add("meat_sqft");
		thirdStoreAttribute.add("frozen_sqft");
		thirdStoreAttribute.add("grocery_sqft");
		thirdStoreAttribute.add("store_sqft");
		store.addAttributes("Store Sqft",true, thirdStoreAttribute);
		
		// Product Dimension
		ArrayList<String> firstProductAttribute  = new ArrayList<String>();
		firstProductAttribute.add("SKU_number");
		firstProductAttribute.add("package_size");
		firstProductAttribute.add("package_type");
		firstProductAttribute.add("diet_type");
		firstProductAttribute.add("weight");
		firstProductAttribute.add("weight_unit_of_measure");
		firstProductAttribute.add("shelf_width_cm");
		firstProductAttribute.add("shelf_height_cm");
		firstProductAttribute.add("shelf_depth_cm");
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
	
		// Time Dimension
		ArrayList<String> firstTimeAttribute  = new ArrayList<String>();	
		firstTimeAttribute .add("holiday_flag");
		time.addAttributes("Time", false, firstTimeAttribute);
		
		ArrayList<String> secondTimeAttribute  = new ArrayList<String>();
		secondTimeAttribute.add("date");
		secondTimeAttribute.add("day_of_week");
		secondTimeAttribute.add("day_number_in_month");
		secondTimeAttribute.add("day_number_overall");
		secondTimeAttribute.add("week_number_in_year");
		secondTimeAttribute.add("week_number_overall");
		secondTimeAttribute.add("Month");
		secondTimeAttribute.add("quarter");
		secondTimeAttribute.add("fiscal_period");
		secondTimeAttribute.add("year");
		time.addAttributes("Time Year", true, secondTimeAttribute);
		
		// Promotion Dimension
		ArrayList<String> firstPromotionAttribute  = new ArrayList<String>();	
		firstPromotionAttribute.add("promotion_name");
		firstPromotionAttribute.add("price_reduction_type");
		firstPromotionAttribute.add("ad_type");
		firstPromotionAttribute.add("display_type");
		firstPromotionAttribute.add("coupon_type");
		firstPromotionAttribute.add("ad_media_type");
		firstPromotionAttribute.add("display_provider");
		firstPromotionAttribute.add("promo_cost");
		firstPromotionAttribute.add("promo_begin_date");
		firstPromotionAttribute.add("promo_end_date");
		promotion.addAttributes("Promotion", false, firstPromotionAttribute);
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

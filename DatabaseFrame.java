import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseFrame extends JFrame{

	private static Connection connection;
	private static PreparedStatement preparedStatement = null;
	
	private DimensionPanel store,product,promotion,time;
	private ArrayList<String> storeAttributes = new ArrayList<String>();
	private ArrayList<String> productAttributes = new ArrayList<String>();
	private ArrayList<String> promotionAttributes = new ArrayList<String>();
	private ArrayList<String> timeAttributes = new ArrayList<String>();
	JPanel rightPanel;
	
	public DatabaseFrame(final Connection conn)
	{
		connection = conn;
		
		populateAttribute();
		store = new DimensionPanel("Store",storeAttributes);
		product = new DimensionPanel("Product",productAttributes);
		promotion = new DimensionPanel("Promotion",promotionAttributes);
		time = new DimensionPanel("Time",timeAttributes);
		setLayout(new GridLayout(0,2));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(0,2));
		leftPanel.add(store);
		leftPanel.add(product);
		leftPanel.add(promotion);
		leftPanel.add(time);
		
		rightPanel = new JPanel();
		
		this.add(leftPanel);
		this.add(rightPanel);
		
		try {
			updateTable("SELECT * FROM time");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void populateAttribute()
	{
		storeAttributes.add("none");
		storeAttributes.add("name");
		storeAttributes.add("store number");
		storeAttributes.add("store street address");
		storeAttributes.add("city");
		storeAttributes.add("county");
		storeAttributes.add("state");
		storeAttributes.add("zip code");
		storeAttributes.add("district");
		storeAttributes.add("region");
		storeAttributes.add("manager");
		storeAttributes.add("phone");
		storeAttributes.add("fax");
		storeAttributes.add("floor plan");
		storeAttributes.add("photo processing type");
		storeAttributes.add("finance service type");
		storeAttributes.add("first opened date");
		storeAttributes.add("last remodeled date");
		storeAttributes.add("store sqft");
		storeAttributes.add("grocery sqft");
		storeAttributes.add("frozen sqft");
		storeAttributes.add("meat sqft");


		productAttributes.add("none");
		productAttributes.add("description");
		productAttributes.add("full_description");
		productAttributes.add("SKU number");
		productAttributes.add("package size");
		productAttributes.add("brand");
		productAttributes.add("subcategory");
		productAttributes.add("category");
		productAttributes.add("department");
		productAttributes.add("package type");
		productAttributes.add("diet type");
		productAttributes.add("weight");
		productAttributes.add("weight unit of measure");
		productAttributes.add("unit per retail case");
		productAttributes.add("unit per shipping case");
		productAttributes.add("cases per pallet");
		productAttributes.add("shelf width cm");
		productAttributes.add("shelf height cm");
		productAttributes.add("shelf depth cm");


		promotionAttributes.add("none");
		promotionAttributes.add("promotion name");
		promotionAttributes.add("price reduction type");
		promotionAttributes.add("ad type");
		promotionAttributes.add("display type");
		promotionAttributes.add("coupon type");
		promotionAttributes.add("ad media type");
		promotionAttributes.add("display provider ");
		promotionAttributes.add("promo cost");
		promotionAttributes.add("promo begin date");
		promotionAttributes.add("promo end date");

		timeAttributes.add("none");
		timeAttributes.add("date");
		timeAttributes.add("day of week");
		timeAttributes.add("day number in month");
		timeAttributes.add("day number overall");
		timeAttributes.add("week number in y ear");
		timeAttributes.add("week number overall");
		timeAttributes.add("month");
		timeAttributes.add("quarter");
		timeAttributes.add("fiscal period");
		timeAttributes.add("year");
		timeAttributes.add("holiday flag");

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
	public void updateTable(String statement) throws SQLException
	{
		rightPanel.removeAll(); // first clear the existing table
		
		preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
	    JTable table = new JTable(buildTableModel(resultSet));
	    
	    rightPanel.add(new JScrollPane(table)); // add the new table to the panel
	    
	    rightPanel.repaint();
	    rightPanel.revalidate();
	}
}

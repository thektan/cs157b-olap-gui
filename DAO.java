/**
 * Connects to the database.
 * 
 * CS 157B - Spring 2014
 * @version 1, 05/7/2014
 */
import java.sql.*;

public class DAO
{
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";
	public static final String SERVERNAME = "localhost:3306";
	public static final String DATABASE = "project";
	
	private Connection conn;
	private PreparedStatement ps;
	
	/**
	 * Create a connection and prepare the statements.
	 * @throws SQLException
	 */
	public DAO() throws SQLException 
	{
		conn = Connect();
	}
	
	/**
	 * Connect to the database.
	 * @return null
	 */
	public static Connection Connect() 
	{	try 
		{	String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
		} 
		catch (Exception e) 
		{	System.out.println("ERROR, JDBC driver not found.");
		}

		Connection connection;
		String url = "jdbc:mysql://" + SERVERNAME + "/" + DATABASE;

		try 
		{	connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			return connection;
		} 
		catch (Exception e) 
		{	System.out.println("ERROR, connection failed.");
		}
		
		return null;
	}
	
	/**
	 * Executes a statement.
	 * @param s the string to execute.
	 */
	public void execute(String s) throws SQLException 
	{
		ps = conn.prepareStatement(s);
		ps.executeUpdate();
	}

}
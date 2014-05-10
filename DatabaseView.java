import java.sql.Connection;
import javax.swing.JFrame;

public class DatabaseView {


	public static void main(String[] args)
	{
		Connection c = DAO.Connect();

		DatabaseFrame view = new DatabaseFrame(c);
		view.setSize(1600,600);
		view.setTitle("dataBase");
		//view.setResizable(false);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
	}
}

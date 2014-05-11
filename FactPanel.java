/**
 * Displays the attributes of the fact table.
 * Also shows a button for slice/dice operations and
 * an execute button to update the table with the new
 * SQL statement.
 * 
 * CS 157B - Spring 2014
 * @author Vinh Doan, Farjahan Hossain, Kevin Tan
 */
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class FactPanel extends JPanel
{
	JPanel radioPanel;
	JButton query, sliceDice;
	ArrayList<DimensionPanel> dimensionPanelList;
	
	public FactPanel()
	{
		// Button for slice/dice operations.
		sliceDice = new JButton("Slice/Dice");
		sliceDice.addActionListener(sliceDiceListener());
		
		// Query button to get the selected values and update the table.
		query = new JButton("Execute");
		query.addActionListener(queryClickEvent());
		
		radioPanel = new JPanel(new GridLayout(0,1,3,3));
		radioPanel.setBorder(BorderFactory.createTitledBorder("Sales Fact"));
		JScrollPane scrPane = new JScrollPane(radioPanel);
		scrPane.setPreferredSize(new Dimension(180,150));
		
		this.add(scrPane);
		this.add(sliceDice);
		this.add(query);
		
		onCreate();
	}
	
	public void linkDimension(ArrayList<DimensionPanel> dimensionPanelList)
	{
		this.dimensionPanelList = dimensionPanelList;
	}
	
	/**
	 * Query button action listener.
	 */
	public ActionListener queryClickEvent()
	{
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				// Populate the lists.
				for(int i = 0; i < dimensionPanelList.size(); i++)
				{
					//System.out.println(dimensionPanelList.get(i).retrieveInput() + dimensionPanelList.get(i).getSelection());
					dimensionPanelList.get(i).retrieveInput(dimensionPanelList.get(i).getSelection());
					DatabaseFrame.model.dimensions_boolean.add(dimensionPanelList.get(i).getSelection());
				}
				
				// Execute the query.
				try { DatabaseFrame.model.execute(); } 
				catch (SQLException e) { e.printStackTrace();}
				
				DatabaseFrame.model.clear(); // Clear the current values.
			}
		};
	}
	
	public void onCreate()
	{
		final ButtonGroup radioGroup  = new ButtonGroup();
		ArrayList<String> factList = new ArrayList<String>();
		factList.add("dollar_sales");
		factList.add("unit_sales");
		factList.add("dollar_cost");
		factList.add("customer_count");
		for(int i = 0; i < factList.size(); i++)
		{		
			String name = factList.get(i);			
			JRadioButton tempButton = new JRadioButton(name);

			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//System.out.println(arg0.getActionCommand());
					DatabaseFrame.model.fact_string = arg0.getActionCommand();
				}
			});
			radioGroup.add(tempButton);
			radioPanel.add(tempButton);
		}		
	}
	
	/**
	 * Asks user for input for attribute and value.
	 * @return
	 */
	public ActionListener sliceDiceListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String tempInput1 =  JOptionPane.showInputDialog(radioPanel
						,"Enter the attribute");
				String tempInput2 =  JOptionPane.showInputDialog(radioPanel
						,"Enter the value");
				
				if(tempInput1 != null && tempInput2 != null)
				{
					String tempString = tempInput1 +" = '"+tempInput2+"'";
					System.out.println(tempString);
					DatabaseFrame.model.slice_dice_list.add(tempString);
				}
			}
		};		
	}
}

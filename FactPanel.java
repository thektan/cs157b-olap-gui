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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;


public class FactPanel extends JPanel{
	
	JPanel radioPanel;
	JButton query;
	ArrayList<DimensionPanel> dimensionPanelList;
	
	public FactPanel()
	{
		query = new JButton("query");
		query.addActionListener(queryClickEvent());
		radioPanel = new JPanel(new GridLayout(0,1,3,3));
		radioPanel.setBorder(BorderFactory.createTitledBorder("Sales Fact"));
		JScrollPane scrPane = new JScrollPane(radioPanel);
		scrPane.setPreferredSize(new Dimension(180,150));
		this.add(scrPane);	
		this.add(query);
		onCreate();
	}
	
	public void linkDimension(ArrayList<DimensionPanel> dimensionPanelList)
	{
		this.dimensionPanelList = dimensionPanelList;
	}
	
	
	public ActionListener queryClickEvent()
	{
		
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				
				DatabaseFrame.model.clear(); // Clear the current values.
				
				// Populate the lists.
				for(int i = 0; i < dimensionPanelList.size(); i++)
				{
					//System.out.println(dimensionPanelList.get(i).retrieveInput() + dimensionPanelList.get(i).getSelection());
					dimensionPanelList.get(i).retrieveInput();
					DatabaseFrame.model.dimensions_boolean.add(dimensionPanelList.get(i).getSelection());
				}
				

				try {
					DatabaseFrame.model.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		factList.add("customer_Count");
		for(int i = 0; i < factList.size();i++)
		{		
			String name = factList.get(i);			
			JRadioButton tempButton = new JRadioButton(name);

			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//System.out.println(arg0.getActionCommand());
				}

			});
			radioGroup.add(tempButton);
			radioPanel.add(tempButton);

		}		
	}
}

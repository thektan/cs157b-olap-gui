import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class AttributePanel extends JPanel {

	String title;
	JPanel radioPanel;
	ArrayList<String> attributeList;
	
	public AttributePanel(String title,ArrayList<String> attribute)
	{
		attributeList = attribute;
		this.title = title;
		radioPanel = new JPanel(new GridLayout(0,1,3,3));
		radioPanel.setBorder(BorderFactory.createTitledBorder(title));
		JScrollPane scrPane = new JScrollPane(radioPanel);
		scrPane.setPreferredSize(new Dimension(180,150));
		this.add(scrPane);
	}
	
	
	
	public void hierarchyCreate()
	{	

		final ButtonGroup radioGroup  = new ButtonGroup();

		for(int i = 0; i < attributeList.size();i++)
		{		
			String name = attributeList.get(i);			
			JRadioButton tempButton = new JRadioButton(name);

			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(title+":"+arg0.getActionCommand());
					
					// Store Selections
					if (title.equals("Store Location-Hierarchy")) DatabaseFrame.store_location = arg0.getActionCommand();
					else if (title.equals("Store Sqft-Hierarchy")) DatabaseFrame.store_sqft = arg0.getActionCommand();
					
					//System.out.println(DatabaseFrame.store_name + " " + DatabaseFrame.store_number + DatabaseFrame.store_location + DatabaseFrame.store_sqft);
				}

			});
			radioGroup.add(tempButton);
			radioPanel.add(tempButton);

		}
	}
	
	public void normalCreate()
	{	

		for(int i = 0; i < attributeList.size();i++)
		{		
			String name = attributeList.get(i);			
			JCheckBox tempButton = new JCheckBox(name);

			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(title+":"+arg0.getActionCommand());
					
					// Store Selections
					if (arg0.getActionCommand().equals("name")) DatabaseFrame.store_name = !DatabaseFrame.store_name;
					else if (arg0.getActionCommand().equals("store_number")) DatabaseFrame.store_number = !DatabaseFrame.store_number;
				}

			});
			radioPanel.add(tempButton);

		}
	}
	
}

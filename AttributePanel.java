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

	
	Boolean isHierarchy;
	String title;
	JPanel radioPanel;
	ArrayList<String> attributeList;
	ArrayList<JRadioButton> radioList;
	ArrayList<JCheckBox> checkBoxList; 
	
	public AttributePanel(String title,boolean isHierarchy,ArrayList<String> attribute)
	{
		this.isHierarchy = isHierarchy;
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
		radioList  = new ArrayList<JRadioButton>(); 
		
		for(int i = 0; i < attributeList.size();i++)
		{		
			String name = attributeList.get(i);			
			JRadioButton tempButton = new JRadioButton(name);
			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//System.out.println(arg0.getActionCommand());
					
					// Store Selections
					if (title.equals("Store Location-Hierarchy")) DatabaseFrame.store_location = arg0.getActionCommand();
					else if (title.equals("Store Sqft-Hierarchy")) DatabaseFrame.store_sqft = arg0.getActionCommand();
					
					//System.out.println(DatabaseFrame.store_name + " " + DatabaseFrame.store_number + DatabaseFrame.store_location + DatabaseFrame.store_sqft);

				}
			});
			radioList.add(tempButton);
			radioGroup.add(tempButton);
			radioPanel.add(tempButton);

		}
	}
	
	public void normalCreate()
	{	

		checkBoxList = new ArrayList<JCheckBox>();
		for(int i = 0; i < attributeList.size();i++)
		{		
			String name = attributeList.get(i);			
			final JCheckBox tempButton = new JCheckBox(name);
			
			tempButton.setActionCommand(name);
			tempButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(tempButton.isSelected())
					{
						//System.out.println(arg0.getActionCommand());
						
						if (arg0.getActionCommand().equals("name")) DatabaseFrame.store_name = !DatabaseFrame.store_name;
						else if (arg0.getActionCommand().equals("store_number")) DatabaseFrame.store_number = !DatabaseFrame.store_number;
					}
				}
			});
			radioPanel.add(tempButton);
			checkBoxList.add(tempButton);
		}
	}
	
	public String retrieveInput()
	{
		//System.out.println("test");
		String input="";
		if(isHierarchy)
		{
			for(int i =0; i < radioList.size();i++ )
			{
				JRadioButton tempButton = radioList.get(i);
				
				if(tempButton.isSelected())
				{
					input = tempButton.getActionCommand();
				}	
			}
		}else
		{
			for(int i =0; i < checkBoxList.size();i++ )
			{
				JCheckBox tempButton = checkBoxList.get(i);
				
				if(tempButton.isSelected())
				{
					//input = input + tempButton.getActionCommand();
					if(!tempButton.getActionCommand().isEmpty())
						DatabaseFrame.model.attributes.add(tempButton.getActionCommand());
				}		
			}
		}	
		return input;
	}
	
}

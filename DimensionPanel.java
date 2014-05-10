import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DimensionPanel extends JPanel {

	JCheckBox dimension; 
	JButton slice,dice;
	String titleDimension;
	ArrayList<AttributePanel> attributePanelList;
	Boolean isSelected = false;
	JPanel firstPanel;
	
	ArrayList<String> sliceList;
	ArrayList<String> diceList;
	
	public DimensionPanel(String title)
	{
		titleDimension = title;
		attributePanelList = new ArrayList<AttributePanel>();
		sliceList = new ArrayList<String>();
		diceList = new ArrayList<String>();
		onCreate();		
	}
	
	
	public void onCreate()
	{	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		firstPanel = new JPanel();
		firstPanel.setLayout((new BoxLayout(firstPanel, BoxLayout.Y_AXIS)));
		dimension = new JCheckBox(titleDimension);
		dimension.addActionListener(dimensionIsSelected());
		slice = new JButton("Slice");
		slice.addActionListener(sliceListener());
		dice = new JButton("dice");
		dice.addActionListener(diceListener());
		firstPanel.add(dimension);
		firstPanel.add(slice);
		firstPanel.add(dice);
		add(firstPanel);
	}
	
	public Boolean getSelection()	{return isSelected;}
	
	public ActionListener sliceListener()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String tempInput1 =  JOptionPane.showInputDialog(firstPanel
						,"Enter the attribute");
				String tempInput2 =  JOptionPane.showInputDialog(firstPanel
						,"Enter the value");
				
				//System.out.println(tempInput1 + " -- " + tempInput2);
				
				if(tempInput1 != null && tempInput2 != null)
				{
					String tempString = tempInput1 +" = '"+tempInput2+"'";
					System.out.println(tempString);
					DatabaseFrame.model.slice_list.add(tempString);
				}

			}
		};		
	}
	
	
	public ActionListener diceListener()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String tempInput1 =  JOptionPane.showInputDialog(firstPanel
						,"Enter the attribute");
				String tempInput2 =  JOptionPane.showInputDialog(firstPanel
						,"Enter the value");
				
				//System.out.println(tempInput1 + " -- " + tempInput2);
				
				if(tempInput1 != null && tempInput2 != null)
				{
					String tempString = tempInput1 +" = '"+tempInput2+"'";
					System.out.println(tempString);
					diceList.add(tempString);
				}

			}
		};		
	}	
	
	
	
	public ActionListener dimensionIsSelected()
	{

		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dimension.isSelected())
				{
					isSelected = true;
				}else
				{
					isSelected = false;
				}
			}
		};
	}
	
	
	public void addAttributes(String title,Boolean isHierarchy,ArrayList<String> attributeList)
	{
		AttributePanel tempAttribute;
		String tempTitle;
		if(isHierarchy)
		{
			tempTitle = title+ "-Hierarchy";
			tempAttribute = new AttributePanel(tempTitle,isHierarchy,attributeList);
			
			tempAttribute.hierarchyCreate();
		}else
		{
			tempTitle = title + "-Normal Attribute";
			tempAttribute = new AttributePanel(tempTitle,isHierarchy,attributeList);
			
			tempAttribute.normalCreate();
		}
		
		attributePanelList.add(tempAttribute);
		add(tempAttribute);
	}
	/*
	public String retrieveInput()
	{
		String input = titleDimension+ "--";
		for(int i = 0; i < attributePanelList.size();i++)
		{
			input= input + attributePanelList.get(i).retrieveInput()+"::";
		}
		
		return input;
		
	}
	*/
	
	/**
	 * Retrieve the input and add the values to the attribute array.
	 */
	public void retrieveInput()
	{
		for(int i = 0; i < attributePanelList.size(); i++)
		{
			// Only add the input if the input isn't empty.
			if(!attributePanelList.get(i).retrieveInput().isEmpty())
				DatabaseFrame.model.attributes.add(attributePanelList.get(i).retrieveInput());
		}
	}
	
}

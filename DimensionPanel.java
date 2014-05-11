/**
 * Panel for the grouping of the attributes for
 * each dimension.
 * 
 * CS 157B - Spring 2014
 * @author Vinh Doan, Farjahan Hossain, Kevin Tan
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class DimensionPanel extends JPanel 
{
	JCheckBox dimension; 
	String titleDimension;
	ArrayList<AttributePanel> attributePanelList;
	boolean isSelected = false;
	JPanel firstPanel;
	
	/**
	 * Creates the dimension panel.
	 * @param title the dimension name.
	 */
	public DimensionPanel(String title)
	{
		titleDimension = title;
		attributePanelList = new ArrayList<AttributePanel>();
		onCreate();		
	}
	
	/**
	 * Creates the panels.
	 */
	public void onCreate()
	{	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		firstPanel = new JPanel();
		firstPanel.setLayout((new BoxLayout(firstPanel, BoxLayout.Y_AXIS)));
		dimension = new JCheckBox(titleDimension);
		dimension.addActionListener(dimensionIsSelected());
		firstPanel.add(dimension);
		add(firstPanel);
	}
	
	/**
	 * Gets the selection is selected.
	 * @return true if the selection is selected and false if it is not.
	 */
	public Boolean getSelection()	{ return isSelected; }
	
	/**
	 * Switches if the selection when it is selected or unselected.
	 */
	public ActionListener dimensionIsSelected()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (dimension.isSelected()) { isSelected = true; }
				else { isSelected = false; }
			}
		};
	}
	
	/**
	 * Adds the attribute panels.
	 * @param title the name of the attribute.
	 * @param isHierarchy if the attributes are hierarchical.
	 * @param attributeList the set of attributes.
	 */
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

	/**
	 * Retrieve the input and add the values to the attribute array.
	 * @param dimension checks if dimension is selected.
	 */
	public void retrieveInput(boolean dimension)
	{
		if (dimension) // Only add the attributes if dimension is selected.
			for(int i = 0; i < attributePanelList.size(); i++)
			{
				// Only add the input if the input isn't empty.
				if(!attributePanelList.get(i).retrieveInput().isEmpty())
					DatabaseFrame.model.attributes.add(attributePanelList.get(i).retrieveInput());
			}
	}
	
}

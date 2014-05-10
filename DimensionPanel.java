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
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DimensionPanel extends JPanel 
{
	JCheckBox dimension; 
	String titleDimension;
	ArrayList<AttributePanel> attributePanelList;
	Boolean isSelected = false;
	JPanel firstPanel;
	
	public DimensionPanel(String title)
	{
		titleDimension = title;
		attributePanelList = new ArrayList<AttributePanel>();
		onCreate();		
	}
	
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
	
	public Boolean getSelection()	{ return isSelected; }
	
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

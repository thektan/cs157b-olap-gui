import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class DimensionPanel extends JPanel {

	JCheckBox dimension; 
	JButton slice,dice;
	String titleDimension;
	ArrayList<AttributePanel> attributePanelList;
	public DimensionPanel(String title)
	{
		titleDimension = title;
		attributePanelList = new ArrayList<AttributePanel>();
		onCreate();		
	}
	
	
	public void onCreate()
	{	
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout((new BoxLayout(firstPanel, BoxLayout.Y_AXIS)));
		dimension = new JCheckBox(titleDimension);
		slice = new JButton("Slice");
		dice = new JButton("dice");
		firstPanel.add(dimension);
		firstPanel.add(slice);
		firstPanel.add(dice);
		add(firstPanel);
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
	
	public String retrieveInput()
	{
		String input = titleDimension;
		for(int i = 0; i < attributePanelList.size();i++)
		{
			input.concat(attributePanelList.get(i).retrieveInput()+"::");
		}
		
		return input;
		
	}
	
}

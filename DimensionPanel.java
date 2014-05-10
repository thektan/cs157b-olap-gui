import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class DimensionPanel extends JPanel {

	JCheckBox dimension; 
	JButton slice,dice;
	String titleDimension;
	
	public DimensionPanel(String title)
	{
		titleDimension = title;
	
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
	
	public void addAttributes(String title,Boolean isHiearchy,ArrayList<String> attributeList)
	{
		String tempTitle;
		if(isHiearchy)
		{
			tempTitle = title+ "-Hierarchy";
			addHiearchyAttributes(tempTitle,attributeList);
		}else
		{
			tempTitle = title + "-Normal Attribute";
			addNormalAttributes(tempTitle,attributeList);
		}
	}
	
	
	public void addHiearchyAttributes(String title, ArrayList<String> attributeList)
	{
		AttributePanel tempAttribute = new AttributePanel(title,attributeList);
		tempAttribute.hierarchyCreate();
		add(tempAttribute);
	}
	
	public void addNormalAttributes(String title, ArrayList<String> attributeList)
	{
		AttributePanel tempAttribute = new AttributePanel(title,attributeList);
		tempAttribute.normalCreate();
		add(tempAttribute);
	}
}

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;


public class DimensionPanel extends JPanel{

	String title;
	JPanel radioPanel;
	ArrayList<String> attributeList;

	public DimensionPanel(String title,ArrayList<String> attribute)
	{
		attributeList = attribute;
		this.title = title;
		radioPanel = new JPanel(new GridLayout(0,1,3,3));
		radioPanel.setBorder(BorderFactory.createTitledBorder(title));
		JScrollPane scrPane = new JScrollPane(radioPanel);
		scrPane.setPreferredSize(new Dimension(200,250));
		this.add(scrPane);
		OnCreate();




	}

	public void OnCreate()
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
				}

			});
			radioGroup.add(tempButton);
			radioPanel.add(tempButton);

		}
	}

}

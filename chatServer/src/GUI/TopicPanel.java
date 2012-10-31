/**
 * 
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import data.Topic;
import data.User;

/**
 * @author User
 *
 */
public class TopicPanel extends JPanel {

	ArrayList<Topic> topic = new ArrayList<Topic>();
	
	public TopicPanel() {
		// temp woring
		topic.add(new Topic());
		topic.add(new Topic());	
		setup();
	}
	
	

	public TopicPanel(User from, User to){
		//load topics that are stored in the from to user
		setup();
	}
	
	private void setup() {
		this.setMinimumSize(new Dimension(300,500));
		this.setBackground(Color.BLUE);
		this.setLayout(new GridLayout(topic.size(),0));
		for(Topic t :topic){
			add(addButton(t));
		}
		
	}

	private JButton addButton(Topic t) {
		;
		JButton button = new JButton(t.getName());
		button.setBorder(BorderFactory.createLoweredBevelBorder());
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		return button;
	}



}

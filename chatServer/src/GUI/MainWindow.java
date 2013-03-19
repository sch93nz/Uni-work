/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import data.User;

/**
 * @author Matthew
 *
 */
public class MainWindow extends JFrame {

	public MainWindow(){
		super("CHAT");
		setMinimumSize(new Dimension(500,700)); 
		add(new contactsPanel(new User()),BorderLayout.EAST);
		add(new TopicPanel(),BorderLayout.CENTER);
		this.pack();
		setVisible(true);
	}
	
	
public static void main (String[] args){
	new MainWindow();
}
	
}

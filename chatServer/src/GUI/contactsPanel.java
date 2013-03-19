package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import data.User;

public class contactsPanel extends JPanel {

	private User user ;
	private ArrayList<User> friends;

	public contactsPanel(User user){
		this.user=user;
		getFriends();
		setMinimumSize(new Dimension(200,700));
		//setLayout(new GridLayout(3,0));
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);

		this.add(avatar());

		this.add(friendsPanel());

	}

	private JLabel avatar() {
		Border compound;

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel);
		JLabel ava;
		if(user.getBigAvatar()!=null){
			ava =user.getBigAvatar();
		} else {
			ava =new JLabel(new ImageIcon("Resources/General.jpg"));
		}

		ava.setBorder(compound);
		return ava;
	}

	private JPanel friendsPanel() {
		JPanel panel = new JPanel();
		Border compound;

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel);
		panel.setBorder(compound);

		panel.setMinimumSize(new Dimension(200,400));
		panel.setBackground(Color.white);

		if (user.HasFriends()){
			panel.setLayout(new GridLayout(user.getFriends().size(),0));
			for (User f :user.getFriends()){
				// add button so and so on
			}
		}else{
			panel.setLayout(new GridLayout(1,0));
		}




		return panel;
	}

	private void getFriends() {
		if (user.HasFriends())
			friends.addAll(user.getFriends());

	}

}

/**
 * 
 */
package data;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;

/**
 * @author User
 *
 */
public class User {
	
	private JLabel bigAvatar; // this is the picture for them that they see or there friends see on a private topic
	private JLabel smallAvatar; // this is the picture they may see on the topics
	private String Username;//the users name
	private ArrayList<Topic> Topics = new ArrayList<Topic>();	// the topics that user has
	private ArrayList<User> Friends = new ArrayList<User>();	// the list of users this user knows
	private boolean hasFriends = false;
	
	/**
	 * @return the bigAvatar
	 */
	public JLabel getBigAvatar() {
		return bigAvatar;
	}

	/**
	 * @param bigAvatar the bigAvatar to set
	 */
	public void setBigAvatar(JLabel bigAvatar) {
		this.bigAvatar = bigAvatar;
	}

	/**
	 * @return the smallAvatar
	 */
	public JLabel getSmallAvatar() {
		return smallAvatar;
	}

	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @return the topics
	 */
	public ArrayList<Topic> getTopics() {
		return Topics;
	}

	/**
	 * @return the friends
	 */
	public ArrayList<User> getFriends() {
		return Friends;
	}

	/**
	 * @return the hasFriends
	 */
	public boolean HasFriends() {
		return hasFriends;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		return true;
	}

}

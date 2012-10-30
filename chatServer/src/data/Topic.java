/**
 * 
 */
package data;
import java.util.ArrayList;

/**
 * @author User
 * stolen form Victoria University of Wellington NWEN243 assignment
 *
 */
public class Topic {
	// name of the topic
	private String name;

	// messages on the topic board
	private ArrayList<Message> messages = new ArrayList<Message>();

	// create a named topic
	public Topic(String _name) 
	{
		name = _name;
	}

	// create a named topic
	public Topic(String _name, ArrayList<Message> mes) 
	{
		name = _name;
		messages.addAll(mes);
	}

	// get the name of the topic
	public String getName() {
		return name;
	}

	// add a message under the topic
	public synchronized void post(String _message) {
		messages.add(new Message(_message));

	}

	// remove a message from under a topic by ID
	public synchronized boolean remove(int _id) {
		boolean result = false;
		int index = messages.indexOf(new Message(_id, null));
		if (index != -1) {
			messages.remove(index);
			result = true;
		}
		return result;
	}

	// return messages stored under a topic
	public Message[] getMessages() {
		return messages.toArray(new Message[messages.size()]);
	}

	public int numberOFMessages(){
		return messages.size();
	}

	// Test method
	public static void main(String[] args) {
		// create a topic and add messages
		Topic t = new Topic("a topic");
		t.post("message 1");
		t.post("message 2");
		t.post("message 3");
		// check that the messages have been added in order
		for (Message s: t.getMessages()) {
			System.out.println(s);
		}
		// should remove message with ID 1 (not same as index)
		System.out.println(t.remove(1));
		// should remove message with ID 3 (not same as index)
		System.out.println(t.remove(3));
		// should fail because already removed
		System.out.println(t.remove(1));
		// check that the message actually has been removed
		for (Message s: t.getMessages()) {
			System.out.println(s);
		}
	}
}

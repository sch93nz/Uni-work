package data;
/**
 * 
 * @author User
 *stolen form Victoria University of Wellington NWEN243 assignment
 *but edited by me for my porpuses
 */
// Represents a message on the bulletin board
public class Message
{
	// current largest message ID, count from 1
	private static int currentID = 1;

	// message ID
	private int id;

	//who its from
	private String from;

	//who this message is to
	private String to;

	// message contents
	private String message;

	// create a message with a given ID
	public Message(int _id, String _message, String from,String to) {
		id = _id;
		message = _message;
		currentID =id;
		this.from=from;
		this.to=to;
	}

	// create a message
	public Message(String _message,String from, String to){
		this.from=from;
		this.to=to;	    	
		id = incrID();
		message = _message;
	}

	// get the ID for the message
	public int getID() {
		return id;
	}

	// get the message contents
	public String getMessage() {
		return message;
	}

	// increment the message counter
	public static synchronized int incrID() {
		return currentID++;
	}

	// modify equals to simplify removal by ID
	public boolean equals(Object _m) {
		boolean result = false;
		if (_m instanceof Message) {
			Message m = (Message)_m;
			result = this.getID() == m.getID();
		}
		return result;
	}

	// have to implement hash because we are going to use contains
	public int hashCode() {
		return this.getID();
	}

	// nice string representation
	public String toString() {
		return this.getID() + ":" + this.getMessage();
	}
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	// Test method
	public static void main(String[] args) {
		Message m1 = new Message("hello 1","test","test2");
		Message m2 = new Message("hello 2","test","test2");
		Message m3 = new Message(1,"hello 3","test","test2");
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m1.equals(m2));
		System.out.println(m2.equals(m2));
		System.out.println(m2.equals(m3));
	}
}
